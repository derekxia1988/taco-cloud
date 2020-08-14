package com.topjoy.tacocloud.data;

import com.topjoy.tacocloud.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {
//    Iterable<Ingredient> findAll();
//    Ingredient findOne(String id);
//    Ingredient save(Ingredient ingredient);
}
