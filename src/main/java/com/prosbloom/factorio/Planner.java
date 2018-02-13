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


        Item[] items = om.readValue(itemsJson, Item[].class);
        ItemRegistry.dumpItems();
        // for (Item i : items )
        //     log.info("item: " + i);
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
