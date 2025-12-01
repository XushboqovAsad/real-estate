package com.example.realestate.mapper;

import com.example.realestate.dto.UserDto;
import com.example.realestate.entity.User;

public class UserMapper {

    public static UserDto toDto(User user) {
        if (user == null) return null;
        return UserDto.builder()
                .id(user.getId())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .avatarUrl(user.getAvatarUrl())
                .role(user.getRole().name())
                .authProvider(user.getAuthProvider().name())
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
