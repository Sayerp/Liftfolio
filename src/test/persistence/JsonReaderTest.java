package persistence;

import org.junit.jupiter.api.Test;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;
import model.Exercise;
import model.Workout;
import model.WorkoutHistory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExcludeFromJacocoGeneratedReport
// Tests in this class are inspired from JsonSerializaitonDemo
public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noFile.json");
        try {
            reader.read();
            fail("IOException expected");
        } catch (Exception e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkoutHistory() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyWorkoutHistory.json");
        try {
            WorkoutHistory wh = reader.read();
            assertEquals(0, wh.getWorkouts().size());
        } catch (Exception e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkoutHistory() {
        JsonReader reader = new JsonReader("data/testReaderGeneralWorkoutHistory.json");
        try {
            WorkoutHistory wh = reader.read();
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
        } catch (Exception e) {
            fail("Couldn't read from file");
        }
    }
}
