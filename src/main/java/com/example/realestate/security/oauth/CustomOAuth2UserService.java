package com.example.realestate.security.oauth;

import com.example.realestate.entity.AuthProvider;
import com.example.realestate.entity.Role;
import com.example.realestate.entity.User;
import com.example.realestate.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest){

        OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);
        CustomOAuth2User customOAuth2User = new CustomOAuth2User(oAuth2User);

        String email = customOAuth2User.getEmail();

        User existingUser = userRepository.findByEmail(email).orElse(null);

        if (existingUser == null) {
            User newUser = User.builder()
                    .fullName(customOAuth2User.getName())
                    .email(email)
                    .avatarUrl(customOAuth2User.getPicture())
                    .authProvider(AuthProvider.GOOGLE)
                    .role(Role.USER)
                    .build();
        }
        return customOAuth2User;
    }
}
