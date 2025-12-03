package com.example.realestate.mapper;

import com.example.realestate.dto.UserDto;
import com.example.realestate.entity.User;

import java.util.stream.Collectors;

public class UserMapper {

    public static UserDto toDto(User user) {
        if (user == null) return null;
        return UserDto.builder()
                .id(user.getId())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .avatarUrl(user.getAvatarUrl())
                .authProvider(user.getAuthProvider().name())
                .roles(user.getRoles()
                                .stream()
                                .map(roleEntity -> roleEntity.getName().name())
                                .collect(Collectors.toSet())
                )
                .build();
    }
    public static User toEntity(UserDto dto) {
        if (dto == null) return null;
        return User.builder()
                .id(dto.getId())
                .fullName(dto.getFullName())
                .email(dto.getEmail())
                .avatarUrl(dto.getAvatarUrl())
                .build();
    }
}
