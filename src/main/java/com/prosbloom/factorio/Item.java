package com.prosbloom.factorio;


public class Item {
    private String name;
    private Recipe recipe;

    public Item(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setRecipe() {
    }
    public Recipe getRecipe() {
        return recipe;
    }
}
