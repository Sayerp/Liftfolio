package model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

@ExcludeFromJacocoGeneratedReport
public class ExerciseTest {
    private Exercise ex1;
    private Exercise ex2;
    private ArrayList<ExerciseSet> sets1;
    private ArrayList<ExerciseSet> sets2;

    @BeforeEach
    void runBefore() {
        ex1 = new Exercise("test");
        ex2 = new Exercise("multiple sets");

        ex2.addSet(5, 225);
        ex2.addSet(1, 100);
        ex2.addSet(8, 315);
        ex2.addSet(8, 45);

        sets1 = ex1.getSets();
        sets2 = ex2.getSets();
    }

    @Test
    void testConstructor() {
        String name = ex1.getName();
        assertTrue(name.equals("test"));
        assertTrue(sets1.isEmpty());
    }

    @Test
    void testAddSetOnce() {
        assertTrue(sets1.isEmpty());
        ex1.addSet(3, 100.5);
        assertTrue(sets1.size() == 1);
        assertEquals(3, sets1.get(0).getReps());
        assertEquals(100.5, sets1.get(0).getWeight());
    }

    @Test
    void testAddSetTwice() {
        assertTrue(sets1.isEmpty());
        ex1.addSet(1, 200);
        ex1.addSet(5, 315.5);
        assertTrue(sets1.size() == 2);
        assertEquals(1, sets1.get(0).getReps());
        assertEquals(200, sets1.get(0).getWeight());
        assertEquals(5, sets1.get(1).getReps());
        assertEquals(315.5, sets1.get(1).getWeight());
    }

    @Test
    void testRemoveSetOnce() {
        ex1.addSet(5, 225);
        assertTrue(sets1.size() == 1);
        ex1.removeSet(0);
        assertTrue(sets1.size() == 0);
    }

    @Test
    void testRemoveSetOnceWithLeftOver() {
        assertTrue(sets2.size() == 4);
        ex2.removeSet(0);
        assertTrue(sets2.size() == 3);
        assertEquals(100, sets2.get(0).getWeight());
    }

    @Test
    void testRemoveSetTwiceWithLeftOver() {
        assertTrue(sets2.size() == 4);
        ex2.removeSet(0);
        ex2.removeSet(2);
        assertTrue(sets2.size() == 2);
        assertEquals(100, sets2.get(0).getWeight());
        assertEquals(315, sets2.get(1).getWeight());
    }

    @Test
    void testEditSet() {
        assertEquals(5, sets2.get(0).getReps());
        assertEquals(225, sets2.get(0).getWeight());
        ex2.editSet(0, 3, 235);
        assertEquals(3, sets2.get(0).getReps());
        assertEquals(235, sets2.get(0).getWeight());
    }

    @Test
    void testCalculateTotalVolume() {
        double totalV = ex2.calculateTotalVolume();
        assertEquals(4105.0, totalV);
    }

    @Test
    void testCalculateTotalVolumeDecimal() {
        ex1.addSet(5, 225.5);
        ex1.addSet(3, 235);
        double totalV = ex1.calculateTotalVolume();
        assertEquals(1832.5, totalV);
    }

    @Test
    void testCalculateOneRepMaxOneSet() {
        ex1.addSet(3, 135);
        double oneRM = sets1.get(0).estimateOneRepMax();
        assertEquals(148.5, oneRM);
    }

    @Test
    void testCalculateOneRepMaxMultipleSets() {
        double oneRM = ex2.calculateOneRepMax();
        assertEquals(399.0, oneRM);
    }

    @Test
    void testCalculateAverageWeight() {
        double avgWeight = ex2.calculateAverageWeight();
        assertEquals(186.6, avgWeight);
    }
}
