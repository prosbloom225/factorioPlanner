package com.prosbloom.factorio;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;

public class Planner {
    private static final Logger log = Logger.getLogger(Planner.class.getName());

    private static void loadItems() 
    throws JsonProcessingException {
        ObjectMapper om  = new ObjectMapper();
        String json = "{}";

        Item testItem = new Item("testItem");
        Item testItem2 = new Item("testItem2");


        log.info(om.writeValueAsString(testItem));

    }

    public static void main(String[] args) {
        log.info("Begin!");
        try {
            loadItems();
        } catch (Exception e) {
            log.severe(e.toString());
        }


        log.info("Planner complete");
    }
}
