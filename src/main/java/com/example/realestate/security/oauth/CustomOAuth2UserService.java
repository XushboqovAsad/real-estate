package com.example.realestate.security.oauth;

import com.example.realestate.entity.AuthProvider;
import com.example.realestate.entity.Role;
import com.example.realestate.entity.RoleEntity;
import com.example.realestate.entity.User;
import com.example.realestate.repository.RoleRepository;
import com.example.realestate.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    @Transactional
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        CustomOAuth2User custom = new CustomOAuth2User(oAuth2User);

        String email = custom.getEmail();
        User existingUser = userRepository.findByEmail(email).orElse(null);

        if (existingUser == null) {
            RoleEntity userRole = roleRepository.findByName(Role.USER)
                    .orElseGet(() -> roleRepository.save(RoleEntity.builder().name(Role.USER).build()));

            Set<RoleEntity> roles = new HashSet<>();
            roles.add(userRole);

            User newUser = User.builder()
                    .fullName(custom.getName())
                    .email(email)
                    .avatarUrl(custom.getPicture())
                    .authProvider(AuthProvider.GOOGLE)
                    .roles(roles)
                    .build();

            existingUser = userRepository.save(newUser);
        } else {
            // optional: update avatar/fullName if changed
        }

        // Build authorities from user's roles
        List<GrantedAuthority> authorities = existingUser.getRoles().stream()
                .map(re -> new SimpleGrantedAuthority("ROLE_" + re.getName().name()))
                .collect(Collectors.toList());

        // Return ApplicationUserPrincipal containing DB id
        return new ApplicationUserPrincipal(existingUser.getId(), oAuth2User, authorities);
    }


}
