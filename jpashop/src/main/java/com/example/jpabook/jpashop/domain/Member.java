package com.example.jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 엔티티에서는 가급적 Setter를 쓰지말자
 * 모든 연관관계는 무조건 LAZY!!!!!!!!!!!
 * @XToOne은 default가 eager이므로 직접 lazy로 설정해야함!!!!!!!!!!
 * N+1 문제 해결
 * 연관된 entity를 모두 가져오고 싶으면 fetch join이나 엔티티그래프 사용
 */
@Setter
@Getter
@Entity
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    private String name;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member") //order class에 있는 member에 의해 mapping
    private List<Order> orders = new ArrayList<>();



}
