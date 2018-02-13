package com.prosbloom.factorio;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;

public class Planner {
    private static final Logger log = Logger.getLogger(Planner.class.getName());

    private static File itemsJson = new File (System.getProperty("user.dir") + "/src/main/resources/items.json");

    private static void loadItems() 
    throws JsonProcessingException, IOException {
        ObjectMapper om  = new ObjectMapper();
        String json = "{}";

        Item testItem = new Item("testItem", 1);
        Item testItem2 = new Item("testItem2", 2);

        ItemRegistry.addItem(testItem);
        ItemRegistry.addItem(testItem2);

        Item[] items = om.readValue(itemsJson, Item[].class);
        for (Item i : items )
            log.info("item: " + i);

        /*
        testItem.getRecipe().addComponent(testItem2, 2);
        testItem.getRecipe().addComponent(testItem2, 2);
        log.info(testItem.toString());
        */




        // log.info(om.writeValueAsString(testItem));

    }

    public static void main(String[] args) {
        log.info("Begin!");
        try {
            loadItems();
        } catch (Exception e) {
            log.severe(e.toString());
            e.printStackTrace();
        }


        log.info("Planner complete");
    }
}
