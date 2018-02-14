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
    
    private static void plan(Item item, int cnt, int time) {
        log.info("Generating plan to produce: " + cnt + " " + item.getName() + " every " + time + " seconds");

        // calculate desired rate
        double desiredRate = cnt/time;

        // calculate output per cycle
        double perCycle = item.getOutput() / item.getTime();

        // brute force cuz computers
        double rate = perCycle;
        for (int i=1; rate <= desiredRate; i++) {
            System.out.println(rate);
            rate = perCycle * i;
        }
        log.info("Desired Rate: " + desiredRate);
        log.info("Optimized Rate: " + rate);
    }

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
            plan(ItemRegistry.retrieveItem("Copper Cable"), 4, 1);
            plan(ItemRegistry.retrieveItem("Copper Cable"), 14, 1);
            plan(ItemRegistry.retrieveItem("Copper Cable"), 9, 1);
        } catch (Exception e) {
            log.severe(e.toString());
            e.printStackTrace();
        }


        log.info("Planner complete");
    }
}
