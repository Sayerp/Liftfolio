package model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ExerciseSetTest {
    private ExerciseSet es1;
    private ExerciseSet es2;
    private ExerciseSet es3;

    @BeforeEach
    void runBefore() {
        es1 = new ExerciseSet(5, 145);
        es2 = new ExerciseSet(1, 315);
        es3 = new ExerciseSet(3, 405.5);
    }

    @Test
    void testConstructorNoDecimal() {
        assertEquals(5, es1.getReps());
        assertEquals(145, es1.getWeight());
    }

    void testConstructorDecimal() {
        assertEquals(3, es3.getReps());
        assertEquals(405.5, es3.getWeight());
    }

    @Test
    void testCalculateVolumeMoreThanOneRep() {
        double volume = es1.calculateVolume();
        assertEquals(725.0, volume);
    }

    @Test
    void testCalculateVolumeOneRep() {
        double volume = es2.calculateVolume();
        assertEquals(315.0, volume);
    }

    @Test
    void testCalculateVolumeDecimal() {
        double volume = es3.calculateVolume();
        assertEquals(1216.5, volume);
    }

    @Test
    void testEstimateOneRepMaxMoreThanOneRep() {
        double oneRM = es1.estimateOneRepMax();
        assertEquals(169.2, oneRM);
    }

    @Test
    void testEsimateOneRepMaxOneRep() {
        double oneRM = es2.estimateOneRepMax();
        assertEquals(315.0, oneRM);
    }

    @Test
    void testEstimateOneRepMaxDecimal() {
        double oneRM = es3.estimateOneRepMax();
        assertEquals(446.1, oneRM);
    }
}
