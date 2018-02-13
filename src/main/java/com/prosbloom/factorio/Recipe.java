package com.prosbloom.factorio;

import java.util.Map;
import java.util.HashMap;


public class Recipe {
    private Map<Integer, Integer> recipe;

    public Recipe() {
        this.recipe = new HashMap<Integer, Integer>();
    }
    public Recipe(Map<Integer, Integer> recipe) {
        this.recipe = recipe;
    }
    public void addComponent(int item, int cnt) {
        recipe.put(item, new Integer(cnt));
    }
    public Map<Integer, Integer> getRecipe() {
        return recipe;
    }
}
