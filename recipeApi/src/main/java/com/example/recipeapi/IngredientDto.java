package com.example.recipeapi;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class IngredientDto {
    private String ingredient;

    public IngredientDto(String ingredient) {
        this.ingredient = ingredient;
    }
}