package code.challenge.cleanArray;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Rotacion {

    @Test
    void testCleanArrayCorrectly() {
        assertArrayEquals(new Object[]{1, 2, "3", "4", 5}, Rotacion.cleanArray(new Object[]{1, 2, new Object[]{"3", "4"}, 5}));
        assertArrayEquals(new Object[]{1, 2, 3, 4, 5, 6}, Rotacion.cleanArray(new Object[]{1, 2, new Object[]{3,4, new Object[]{5,6}}}));
        assertArrayEquals(new Object[]{1, 2, "a", "b", 5, 6},  Rotacion.cleanArray(new Object[]{1, 2, new Object[]{"a", "b", new Object[]{5,6}}}));
        assertArrayEquals(new Object[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, Rotacion.cleanArray(new Object[]{1, 2, new Object[]{3,4, new Object[]{5,6,new Object[]{7,8, new Object[]{9,10}}}}}));
    }

    @Test
    void testCleanArrayError() {
        assertArrayEquals(new Object[]{1, 2, "3", "4"}, Rotacion.cleanArray(new Object[]{1, 2, new Object[]{"3", "4"}}));
        assertArrayEquals(new Object[]{1, 2, 4, 3, 5, 6}, Rotacion.cleanArray(new Object[]{1, 2, new Object[]{3,4, new Object[]{5,6}}}));
        assertArrayEquals(new Object[]{1, 2, "a", "b", 5, 6},  Rotacion.cleanArray(new Object[]{1, 2, new Object[]{"a", "b", new Object[]{5,6}}}));
        assertArrayEquals(new Object[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, Rotacion.cleanArray(new Object[]{1, 2, new Object[]{3,4, new Object[]{5,6,new Object[]{7,8, new Object[]{9,10}}}}}));
    }
}