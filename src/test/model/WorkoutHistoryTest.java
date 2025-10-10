package model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class WorkoutHistoryTest {
    private WorkoutHistory wh1;
    private WorkoutHistory wh2;
    private ArrayList<Workout> workouts1;
    private ArrayList<Workout> workouts2;
    private Workout w1;
    private Workout w2;
    private Workout w3;

    @BeforeEach
    void runBefore() {
        wh1 = new WorkoutHistory();
        wh2 = new WorkoutHistory();
        w1 = new Workout("Arms");
        w2 = new Workout("Legs");
        w3 = new Workout("Chest");

        wh2.addWorkout(w1);
        wh2.addWorkout(w2);
        wh2.addWorkout(w3);

        workouts1 = wh1.getWorkouts();
        workouts2 = wh2.getWorkouts();
    }

    @Test
    void testConstructor() {
        assertTrue(workouts1.isEmpty());
    }

    @Test
    void testAddWorkoutOnce() {
        assertTrue(workouts1.isEmpty());
        wh1.addWorkout(w1);
        Workout workout = wh1.getWorkout(0); 
        assertTrue(workouts1.size() == 1);
        assertTrue(workout.getName().equals("Arms"));

    }

    @Test
    void testAddWorkoutTwice() {
        assertTrue(workouts1.isEmpty());
        wh1.addWorkout(w1);
        wh1.addWorkout(w2);
        Workout workout1 = wh1.getWorkout(0);
        Workout workout2 = wh1.getWorkout(1);
        assertTrue(workouts1.size() == 2);
        assertTrue(workout1.getName().equals("Arms"));
        assertTrue(workout2.getName().equals("Legs"));
    }

    @Test
    void testRemoveWorkoutOnce() {
        wh1.addWorkout(w1);
        assertTrue(workouts1.size() == 1);
        wh1.removeWorkout(0);
        assertTrue(workouts1.size() == 0);
    }

    @Test
    void testRemoveWorkoutOnceWithLeftOver() {
        assertTrue(workouts2.size() == 3);
        wh2.removeWorkout(0);
        assertTrue(workouts2.size() == 2);
        Workout workout = wh2.getWorkout(0);
        assertTrue(workout.getName().equals("Legs"));
    }

    @Test
    void testRemoveWorkoutTwiceWithLeftOver() {
        assertTrue(workouts2.size() == 3);
        wh2.removeWorkout(0);
        wh2.removeWorkout(0);
        assertTrue(workouts2.size() == 1);
        Workout workout = wh2.getWorkout(0);
        assertTrue(workout.getName().equals("Chest"));
    }

}
