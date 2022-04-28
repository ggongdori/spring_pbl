package com.example.jpabook.jpashop.domain;

import lombok.Getter;

import javax.persistence.Embeddable;


/**
 * 값 타입은 변경 불가능하게 설계해야 한다
 * @Setter를 모두 제거하고 생성자에서 값을 모두 초기화해서 변경 불가능한 클래스를 만들자
 * 엔티티나 임베디드 타입은 자바 기본 생성자를 public or protected로 설정해야 한다
 * protected가 그나마 안전
 * jpa 구현 라이브러리가 객체를 생성할 때 리플렉션 같은 기술을 사용할 수 잇도록
 * 지원해야 하기 때문
 */
@Embeddable
@Getter
public class Address {

    private String city;
    private String street;
    private String zipcode;

    protected Address() {

    }

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
