package com.prosbloom.factorio;

import java.util.Map;
import java.util.HashMap;


public class Recipe {
    private Map<Item, Integer> recipe;

    public Recipe() {
        this.recipe = new HashMap<Item, Integer>();
    }
    public Recipe(Map<Item, Integer> recipe) {
        this.recipe = recipe;
    }
    public void addComponent(Item item, int cnt) {
        recipe.put(item, new Integer(cnt));
    }
    public Map<Item, Integer> getRecipe() {
        return recipe;
    }
}
