package persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;

import model.Exercise;
import model.ExerciseSet;

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
}
