package com.example.jwt_prac;


import lombok.Data;

@Data
public class UserUpdateRequestDto {
    private String email;
    private String password;
    private String phoneNumber;
}
