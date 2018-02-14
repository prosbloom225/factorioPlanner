package com.prosbloom.factorio;

import java.util.Map;
import java.util.HashMap;
import java.util.logging.Logger;


public class Recipe {
    private static final Logger log = Logger.getLogger(Recipe.class.getName());
    private Map<Item, Integer> recipe;

    public Recipe() {
        this.recipe = new HashMap<Item, Integer>();
    }
    public Recipe(Map<Item, Integer> recipe) {
        this.recipe = recipe;
    }
    public void addComponent(int item, int cnt) {
        recipe.put(
                ItemRegistry.retrieveItem(item), 
                new Integer(cnt));
    }
    public void addComponent(String item, int cnt) {
        try {
        log.info("Recipe: " + item + cnt);
        recipe.put(
                ItemRegistry.retrieveItem(item), 
                new Integer(cnt));
        } catch (Exception e) {
            log.severe(e.toString());
        }
    }
    public Map<Item, Integer> getRecipe() {
        return recipe;
    }

    @Override
    public String toString() {
        return recipe.toString();
    }
}
