package com.example.jwt_prac;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Builder
@NoArgsConstructor //파라미터가 없는 기본 생성자
@AllArgsConstructor //모든 필드 값을 파라미터로 받는 생성자
@EntityListeners(AuditingEntityListener.class)
public class ExpiredRefreshToken {
    @Id
    @GeneratedValue
    private Long id;

    private String token;
}
