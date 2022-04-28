package com.example.jpabook.jpashop.domain;

import com.example.jpabook.jpashop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class OrderItem {

    @Id
    @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    //XtoOne 연관관계는 기본이 eager, lazy로 무조건 바꿔야함!!!!!!!!
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    //XtoOne 연관관계는 기본이 eager, lazy로 무조건 바꿔야함!!!!!!!!
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;


    private int orderPrice;
    private int count;
}
