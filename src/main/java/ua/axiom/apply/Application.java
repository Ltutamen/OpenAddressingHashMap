package ua.axiom.apply;

import ua.axiom.apply.map.HashMap;
import ua.axiom.apply.map.OpenAddressingHashMap;

public class Application {
    public static void main(String[] args) {
        HashMap map = new OpenAddressingHashMap();
        map.put(1, 100);
        map.put(2, 200);
        map.put(67, 6700);

        System.out.println("haha");

    }
}
