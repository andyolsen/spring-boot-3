package mypackage;

import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;

@Component
public class Catalog {

    private Map<Integer, Item> itemMap;

    public Catalog() {
        itemMap = new HashMap<>();
        itemMap.put(0, new Item(0, "Apple Mac Book Pro", 2499.99));
        itemMap.put(1, new Item(1, "32GB San Disk", 15.99));
        itemMap.put(2, new Item(2, "OLED 64in TV", 1800));
        itemMap.put(3, new Item(3, "Wireless Mouse", 10.50));
        itemMap.put(4, new Item(4, "Virtual Reality HeadSet", 200));
    }

    public Item getItemById(int id) {
        return itemMap.get(id);
    }
}