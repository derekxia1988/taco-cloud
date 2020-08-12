package com.topjoy.tacocloud.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.topjoy.tacocloud.Ingredient;
import com.topjoy.tacocloud.Order;
import com.topjoy.tacocloud.Taco;
import com.topjoy.tacocloud.data.IngredientRepository;
import com.topjoy.tacocloud.data.TacoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import static com.topjoy.tacocloud.Ingredient.*;

/**
 * Copyright(c) $today.year Topjoy All rights reserved.
 * Project : $module.name
 * Author : xiadong
 * Create : 2020
 * LastModify : $file.lastModified
 **/
@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {

    private final IngredientRepository ingredientRepository;
    private final TacoRepository tacoRepository;
    @Autowired
    public DesignTacoController(IngredientRepository ingredientRepository, TacoRepository tacoRepository) {
        this.ingredientRepository = ingredientRepository;
        this.tacoRepository = tacoRepository;
    }

    @GetMapping
    public String showDesignForm(Model model) {
        List<Ingredient> ingredients = new ArrayList<>();
        ingredientRepository.findAll().forEach(ingredients::add);

        Arrays.stream(Type.values())
            .forEach(type -> model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type)));

        model.addAttribute("design", new Taco());
        return "design";
    }

    @PostMapping
    public String processDesign(@Valid Taco design, Errors errors, @ModelAttribute Order order) {
        log.info("Processing design:" + design);
        if (errors.hasErrors()) {
            return "design";
        }
        Taco saves = tacoRepository.save(design);
        order.addDesign(saves);
        return "redirect:/orders/current";
    }

    @ModelAttribute(name = "order")
    public Order order() {
        return new Order();
    }

    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }

    private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
        return ingredients.stream()
            .filter(x -> x.getType() == type)
            .collect(Collectors.toList());
    }

}
