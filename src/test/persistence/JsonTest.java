package persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import model.Exercise;
import model.ExerciseSet;
import model.Workout;
import model.WorkoutHistory;
import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

@ExcludeFromJacocoGeneratedReport
// This class was inspired from the JsonSerializaitonDemo
public class JsonTest {

    protected void checkExercise(String name, int numSets, Exercise exercise) {
        assertEquals(name, exercise.getName());
        assertEquals(numSets, exercise.getSets().size());
    }

    protected void checkExerciseSet(int reps, double weight, ExerciseSet set) {
        assertEquals(reps, set.getReps());
        assertEquals(weight, set.getWeight());
    }

    protected void checkGeneralWorkoutHistory(WorkoutHistory wh) {
            List<Workout> workouts = wh.getWorkouts();
            assertEquals(2, workouts.size());

            Workout legDay = workouts.get(0);
            assertEquals("Leg Day", legDay.getName());

            Exercise squats = legDay.getExercises().get(0);
            checkExercise("Squats", 2, squats);
            checkExerciseSet(6, 225, squats.getSets().get(0));
            checkExerciseSet(3, 185, squats.getSets().get(1));

            Workout chestDay = workouts.get(1);
            assertEquals("Chest Day", chestDay.getName());

            Exercise bench = chestDay.getExercises().get(0);
            checkExercise("Bench Press", 1, bench);
            checkExerciseSet(5, 315, bench.getSets().get(0));

            Exercise flys = chestDay.getExercises().get(1);
            checkExercise("Cable Flys", 2, flys);
            checkExerciseSet(8, 60, flys.getSets().get(0));
            checkExerciseSet(5, 50, flys.getSets().get(1));
    }
}
