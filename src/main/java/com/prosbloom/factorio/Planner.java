package com.prosbloom.factorio;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Map;
import java.util.HashMap;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;

public class Planner {
    private static final Logger log = Logger.getLogger(Planner.class.getName());

    private static File itemsJson = new File (System.getProperty("user.dir") + "/src/main/resources/items.json");
    

    private static Map<Item, Integer> optimize(Item item, int desiredAmount, double time) {
        // return a map of Item:assembly machines required to produce  desiredAmount/time
        Map<Item, Integer> optimized = new HashMap<Item, Integer>();
        double desiredRate = desiredAmount/time;
        log.info("Optimizing " + item.getName() + " for " + desiredRate + "per second");
        int curr = 0;
        double rate = 0;
        // optimize current item
        do {
            curr++;
            rate = (curr * item.getOutput())/item.getTime();
            log.fine("rate: " + rate);
        } while (rate < desiredRate);
        optimized.put(item, new Integer(curr));

        // optimize components
        for (Map.Entry<Item, Integer> component : item.getRecipe().entrySet()) {
                log.info(component.getKey().getName());

                optimize(component.getKey(), 
                        curr *(component.getValue() * component.getKey().getOutput()), 
                        time).forEach((k, v) -> optimized.merge(k, v, Integer::sum));
        }

        return optimized;
    }
    private static TreeNode<Item> plan(Item item) {
        log.info("Generating plan to produce: " + item.getName());
        TreeNode<Item> node = new TreeNode<Item>(item);
        for (Map.Entry<Item, Integer> component : item.getRecipe().entrySet()) {
            log.fine("cmp: " + component.getKey().getName());
            node.addChild(plan(component.getKey()));
        }

        return node;
    }
    private static Map<Item, Integer> calculate(Item item, int cnt, double time) {
        log.info("Generating plan to produce: " + cnt + " " + item.getName() + " every " + time + " seconds");
        Map<Item, Integer> plan = new HashMap<Item, Integer>();

        // calculate desired rate
        double desiredRate = cnt/time;

        // calculate base cycle rate
        int multi = 1;
        double cycleRate = item.getOutput() / item.getTime();
        log.info("CycleRate: " + cycleRate);

        // calculate output per cycle
        while (multi*cycleRate < desiredRate){
            log.fine("Multiplier: " + multi + " - " + multi*cycleRate);
            ++multi;
        }
        double rate = multi*cycleRate;

        plan.put(item, multi);

        log.info("Desired Rate: " + desiredRate);
        log.info("Optimized Rate: " + rate);


        /*
        // process components
        Map<Item, Integer> componentPlan = new HashMap<Item, Integer>();
        for (Map.Entry<Item, Integer> component : item.getRecipe().entrySet()) {
            log.info("Component: " + component.getKey().getName() + " - " + component.getValue());
            componentPlan.putAll(plan(component.getKey(), component.getValue(), time));

        }
        */
        return plan;
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
            calculate(ItemRegistry.retrieveItem("Copper Cable"), 4, 1);
            calculate(ItemRegistry.retrieveItem("Copper Cable"), 14, 1);
            calculate(ItemRegistry.retrieveItem("Copper Cable"), 9, 1);
            calculate(ItemRegistry.retrieveItem("Basic Circuit Board"), 9, 1);
            TreeNode<Item> plan = plan(ItemRegistry.retrieveItem("Basic Circuit Board"));

            for (TreeNode<Item> node : plan ){
                log.info("LEVEL: " + node.getLevel() + " - " + node.data);
            }
            Map<Item, Integer> optimized = optimize(ItemRegistry.retrieveItem("Basic Circuit Board"), 100, 1);

            log.info("Machines required");
            for (Map.Entry<Item, Integer> item : optimized.entrySet()) {
                log.info(item.getKey().getName() + " - " + item.getValue());
            }


        } catch (Exception e) {
            log.severe(e.toString());
            e.printStackTrace();
        }


        log.info("Planner complete");
    }
}
