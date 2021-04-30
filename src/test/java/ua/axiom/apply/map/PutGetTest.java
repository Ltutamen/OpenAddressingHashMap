package ua.axiom.apply.map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PutGetTest {

    @Test
    public void singularPutTest() {
        final int key = 566;
        final long val = 12345;
        HashMap map = new OpenAddressingHashMap();

        map.put(key, val);

        long mapValue = map.get(key).get();

        Assertions.assertEquals(val, mapValue);
    }

    @Test
    public void randomPutTest() {
        final int entriesSize = 1024;
        HashMap map = new OpenAddressingHashMap();
        MapEntry[] entries = new MapEntry[entriesSize];

        for (int i = 0; i < entriesSize ; ++i) {
            MapEntry newEntry = new MapEntry(i, i * 100);

            entries[i] = newEntry;
            map.put(i, newEntry.getValue());
        }

        for (MapEntry entry : entries) {
            final int key = entry.getKey();
            final long value = entry.getValue();

            long mapValue = map.get(key).get();
            Assertions.assertEquals(value, mapValue);
        }


    }
}
