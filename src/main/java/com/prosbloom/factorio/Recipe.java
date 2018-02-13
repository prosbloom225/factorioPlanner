package com.prosbloom.factorio;

import java.util.Map;
import java.util.HashMap;
import java.util.logging.Logger;


public class Recipe {
    private static final Logger log = Logger.getLogger(Recipe.class.getName());
    private Map<Integer, Integer> recipe;

    public Recipe() {
        this.recipe = new HashMap<Integer, Integer>();
    }
    public Recipe(Map<Integer, Integer> recipe) {
        this.recipe = recipe;
    }
    public void addComponent(int item, int cnt) {
        recipe.put(
                new Integer(item), 
                new Integer(cnt));
    }
    public void addComponent(String item, int cnt) {
        try {
        log.info("Recipe: " + item + cnt);
        recipe.put(
                new Integer(ItemRegistry.retrieveItemId(item)), 
                new Integer(cnt));
        } catch (Exception e) {
            log.severe(e.toString());
        }
    }
    public Map<Integer, Integer> getRecipe() {
        return recipe;
    }

    @Override
    public String toString() {
        return recipe.toString();
    }
}
