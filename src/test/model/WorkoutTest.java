package model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class WorkoutTest {
    private Workout w1;
    private Workout w2;
    private ArrayList<Exercise> exercises1;
    private ArrayList<Exercise> exercises2;
    private Exercise e1;
    private Exercise e2;
    private Exercise e3;

    @BeforeEach
    void runBefore() {
        w1 = new Workout("Arms");
        w2 = new Workout("Legs");
        e1 = new Exercise("Squats");
        e2 = new Exercise("Leg Press");
        e3 = new Exercise("Calf Raise");

        e1.addSet(5, 145);
        e2.addSet(2, 225);

        w2.addExercise(e1);
        w2.addExercise(e2);
        w2.addExercise(e3);

        exercises1 = w1.getExercises();
        exercises2 = w2.getExercises();
    }

    @Test
    void testConstructor() {
        String name = w1.getName();
        assertTrue(name.equals("Arms"));
        assertTrue(exercises1.size() == 0);
    }

    @Test
    void testAddExerciseOnce() {
        assertTrue(exercises1.isEmpty());
        w1.addExercise(e1);
        Exercise ex = w1.getExercises().get(0);
        assertTrue(exercises1.size() == 1);
        assertTrue(ex.getName().equals("Squats"));
    }

    @Test
    void testAddExerciseTwice() {
        assertTrue(exercises1.isEmpty());
        w1.addExercise(e1);
        w1.addExercise(e2);
        Exercise ex1 = w1.getExercises().get(0);
        Exercise ex2 = w1.getExercises().get(1);
        assertTrue(exercises1.size() == 2);
        assertTrue(ex1.getName().equals("Squats"));
        assertTrue(ex2.getName().equals("Leg Press"));
    }

    @Test
    void testRemoveExerciseOnce() {
        w1.addExercise(e1);
        assertTrue(exercises1.size() == 1);
        w1.removeExercise(0);
        assertTrue(exercises1.size() == 0);
    }

    @Test
    void testRemoveExerciseOnceWithLeftOver() {
        assertTrue(exercises2.size() == 3);
        w2.removeExercise(0);
        assertTrue(exercises2.size() == 2);
        Exercise ex1 = w2.getExercises().get(0);
        assertTrue(ex1.getName().equals("Leg Press"));
    }

    @Test
    void testRemoveExerciseTwiceWithLeftOver() {
        assertTrue(exercises2.size() == 3);
        w2.removeExercise(0);
        w2.removeExercise(1);
        assertTrue(exercises2.size() == 1);
        Exercise ex1 = w2.getExercises().get(0);
        assertTrue(ex1.getName().equals("Leg Press"));
    }

    @Test
    void testEditExerciseRepsWeight() {
        Exercise ex1 = w2.getExercises().get(0);
        ExerciseSet set = ex1.getSets().get(0);
        assertEquals(5, set.getReps());
        assertEquals(145, set.getWeight());
        w2.editExercise(0, 0, 1, 175);
        assertEquals(1, set.getReps());
        assertEquals(175, set.getWeight());
    }

    @Test
    void testEditExerciseName() {
        Exercise ex1 = w2.getExercises().get(0);
        assertTrue(ex1.getName().equals("Squats"));
        w2.editExercise(0, "Pull up");
        assertTrue(ex1.getName().equals("Pull up"));
    }
}
