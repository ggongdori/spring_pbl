package com.example.recipeapi;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter // get 함수를 일괄적으로 만들어줍니다.
@NoArgsConstructor // 기본 생성자를 만들어줍니다.
@Entity // DB 테이블 역할을 합니다. //master branch test
public class Ingredient{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String ingredient;

    public Ingredient(IngredientDto ingredientDto) {
        this.ingredient = ingredientDto.getIngredient();
    }

}