package com.example.realestate.dto;

import lombok.Builder;
import lombok.Data;

import java.security.AuthProvider;
import java.util.UUID;

@Data
@Builder
public class UserDto {
    private UUID id;
    private String fullName;
    private String email;
    private String avatarUrl;
    private String role;
    private String authProvider;

}
