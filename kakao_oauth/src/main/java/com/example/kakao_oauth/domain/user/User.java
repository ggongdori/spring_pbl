package com.example.kakao_oauth.domain.user;

import com.example.kakao_oauth.audit.AuditListener;
import com.example.kakao_oauth.audit.Auditable;
import com.example.kakao_oauth.audit.TimeEntity;
import com.example.kakao_oauth.dto.request.UserUpdateRequest;
import com.example.kakao_oauth.oauth.ProviderType;
import lombok.*;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;

import static javax.persistence.EnumType.STRING;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditListener.class)
@AllArgsConstructor
@Entity
@Builder
public class User implements Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @NotNull
    @Column(name = "user_provider_id")
    private String userId;

//    @OneToMany(mappedBy = "user")
//    private List<Post> posts = new ArrayList<>();

    @Enumerated(STRING)
    @NotNull
    private ProviderType providerType;

    private String email;

    @Builder.Default
    private boolean emailCheck = false;

    @NotNull
    private String nickname;

//    private String phoneNum;
//
//    private String birthday;
//
//    private String birthYear;
//
//    @Enumerated(STRING)
//    private Gender gender;

    @Builder.Default
    private boolean deleted = false;

    @Embedded
    private TimeEntity timeEntity;

    @Override
    public void setTimeEntity(TimeEntity timeEntity) {
        this.timeEntity = timeEntity;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void update(UserUpdateRequest userUpdateRequest) {
        this.email = userUpdateRequest.getEmail();
        this.nickname = userUpdateRequest.getNickname();
//        this.phoneNum = userUpdateRequest.getPhoneNum();
//        this.gender = userUpdateRequest.getGender();
    }

    public void updateEmail(String email) {
        this.email = email;
        emailCheck = true;
    }

}