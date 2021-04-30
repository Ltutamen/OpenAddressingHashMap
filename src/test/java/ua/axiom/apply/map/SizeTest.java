package ua.axiom.apply.map;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Random;

public class SizeTest {
    private final Random random = new Random();

    @Test
    public void singularSizeTest() {
        HashMap map = new OpenAddressingHashMap();

        int oldSize = map.size();

        map.put(1, 2);

        int newSize = map.size();

        Assertions.assertEquals(0, oldSize);
        Assertions.assertEquals(1, newSize);
    }

    @Test
    @RepeatedTest(10)
    public void randomSizeTest() {
        HashMap map = new OpenAddressingHashMap();
        final int toWriteCount = random.nextInt(5097);

        for (int i = 0; i < toWriteCount ; ++i) {
            map.put(i, 0);
        }

        int newSize = map.size();
        Assertions.assertEquals(toWriteCount, newSize);
    }
}
