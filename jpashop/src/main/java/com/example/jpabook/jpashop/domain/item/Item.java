package com.example.jpabook.jpashop.domain.item;


import com.example.jpabook.jpashop.domain.Category;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@Entity
public abstract class Item {

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    //컬렉션은 필드에서 초기화!!!!!!!!
    //가급적 변경xxxxxxxxxxxx
    //hibernate 가 관리할 수 있는 객체로 감싸지기 때문에
    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();
}
