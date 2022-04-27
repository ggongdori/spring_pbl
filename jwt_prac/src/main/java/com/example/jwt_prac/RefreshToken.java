package com.example.jwt_prac;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


@NoArgsConstructor
@AllArgsConstructor
@Entity
public class RefreshToken {
    @Id
    @Column(nullable = false)
    private String refreshToken;
}