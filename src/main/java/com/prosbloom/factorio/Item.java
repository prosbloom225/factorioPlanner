package com.prosbloom.factorio;

import java.util.logging.Logger;
import java.util.Map;
import java.util.HashMap;

import java.util.Iterator;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Item {
    private static final Logger log = Logger.getLogger(Item.class.getName());

    private String name;
    private String machine;
    private int time;
    private int output;
    private Recipe recipe;


    public Item() {
        ItemRegistry.register(this);
        recipe = new Recipe();
    }

    @SuppressWarnings("unchecked")
    @JsonProperty("recipe")
    private void unpackNested(Map<String,Object>[] recipe) {
        for (Map<String, Object> m : recipe){
            if (m.get("name") != null) {
                String name = (String)m.get("name");
                int cnt = (int)m.get("cnt");
                log.info("recipe: " + name);
                log.info("recipe: " + cnt);
                this.recipe.addComponent(name, cnt);
            } else {
                log.info("No Recipe found!");
            }
        }
    }


    public void setMachine(String machine) {
        this.machine = machine;
    }
    public String getMachine() {
        return machine;
    }
    public void setTime(int time) {
        this.time = time;
    }
    public int getTime() {
        return time;
    }
    public void setOutput(int output) {
        this.output = output;
    }
    public int getOutput() {
        return output;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    @Override
    public String toString() {
        return "name: " + this.getName() + " - " + this.recipe.toString();
    }
}
