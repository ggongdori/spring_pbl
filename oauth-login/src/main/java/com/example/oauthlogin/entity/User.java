package com.example.oauthlogin.entity;

import com.example.oauthlogin.audit.AuditListener;
import com.example.oauthlogin.audit.Auditable;
import com.example.oauthlogin.audit.TimeEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@Entity
@EntityListeners(AuditListener.class)
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email")
})
@NoArgsConstructor
public class User implements Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Email
    @Column(nullable = false)
    private String email;

    private String imageUrl;

    @Column(nullable = false)
    private Boolean emailVerified = false;

    @JsonIgnore
    private String password;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AuthProvider provider;

    private String providerId;

    @Embedded
    private TimeEntity timeEntity;

    @Builder(builderClassName= "social", builderMethodName = "socialBuilder")
    private User(String name, @Email String email, String imageUrl, @NotNull AuthProvider provider, String providerId) {
        this.name = name;
        this.email = email;
        this.imageUrl = imageUrl;
        this.provider = provider;
        this.providerId = providerId;
    }

    @Builder(builderClassName = "local",builderMethodName = "localBuilder")
    public User(String name, @Email String email, String imageUrl, String password, @NotNull AuthProvider provider, String providerId) {
        this.name = name;
        this.email = email;
        this.imageUrl = imageUrl;
        this.password = password;
        this.provider = provider;
        this.providerId = providerId;
    }

    public void updateNameAndImage(String name, String imageUrl) {
        this.name = name;
        this.imageUrl = imageUrl;
    }
}