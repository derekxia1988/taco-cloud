package com.topjoy.tacocloud.web;

import com.topjoy.tacocloud.Ingredient;
import com.topjoy.tacocloud.data.IngredientRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class IngredientByIdConverter implements Converter<String, Ingredient> {

  private final IngredientRepository ingredientRepo;

  @Autowired
  public IngredientByIdConverter(IngredientRepository ingredientRepo) {
    this.ingredientRepo = ingredientRepo;
  }
  
  @Override
  public Ingredient convert(@NotNull String id) {
    return ingredientRepo.findById(id).orElse(null);
  }

}
