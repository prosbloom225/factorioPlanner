package com.prosbloom.factorio;

import java.util.logging.Logger;
import java.util.Map;
import java.util.HashMap;

import java.util.Iterator;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Item {
    private static final Logger log = Logger.getLogger(Planner.class.getName());

    private String name;
    private int id;
    private Recipe recipe;

    public Item(String name, int id) {
        this.name = name;
        this.id = id;
        this.recipe = new Recipe();
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

    @Override
    public String toString() {
        return "name: " + this.getName();
    }
}
