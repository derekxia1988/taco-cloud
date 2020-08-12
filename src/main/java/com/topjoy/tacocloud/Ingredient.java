package com.topjoy.tacocloud;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * Copyright(c) $today.year Topjoy All rights reserved.
 * Project : $module.name
 * Author :
 * Create : 2020
 * LastModify : $file.lastModified
 **/
@Data
@RequiredArgsConstructor
public class Ingredient {
    private final String id;
    private final String name;
    private final Type type;

    public enum Type {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }
}
