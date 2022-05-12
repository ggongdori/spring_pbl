package com.example.springoauth.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String oAuth2Id;

    private String email;
    private String nickname;
    private String picture;

    @Enumerated(value = EnumType.STRING)
    private Role role;
}
