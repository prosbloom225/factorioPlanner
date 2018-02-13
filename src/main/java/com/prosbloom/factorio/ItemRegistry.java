package com.prosbloom.factorio;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ItemRegistry {
    private static final Logger log = Logger.getLogger(Planner.class.getName());
    protected static List<Item> registry = new ArrayList<Item>();

    public static int register(Item item) {
        registry.add(item);
        log.info("Added item: " + registry.indexOf(item) + "-" + item.getName());
        return registry.indexOf(item);
    }
    public static void removeItem(Item item) {
        registry.remove(item);
        log.info("Removed item: " + item.getName());
    }

    public static Item retrieveItemByName(String name) {
        for (Item i : registry)
            if (i.getName().equals(name))
                return i;
        return null;
    }
    public static int retrieveItemId(String item) {
        return registry.indexOf(retrieveItemByName(item));
    }
    public static int retrieveItemId(Item item) {
        return registry.indexOf(item);
    }
    public static Item retrieveItem(int id) {
        return registry.get(id);
    }
    public static int count(){
        return registry.size();
    }
    public static void dumpItems() {
        log.info("Dumping item registry");
        for (Item i : registry)
            log.info(i.toString());
    }

}

