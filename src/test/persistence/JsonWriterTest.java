package persistence;

import org.junit.jupiter.api.Test;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;
import model.Exercise;
import model.Workout;
import model.WorkoutHistory;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

@ExcludeFromJacocoGeneratedReport
// Tests in this class are inspired from JsonSerializaitonDemo
public class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/an\0IllegalFileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkoutHistory() {
        try {
            WorkoutHistory wh = new WorkoutHistory();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyWorkoutHistory.json");
            writer.open();
            writer.write(wh);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyWorkoutHistory.json");
            wh = reader.read();
            assertEquals(0, wh.getWorkouts().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkoutHistory() {
        try {
            WorkoutHistory wh = makeGeneralWorkoutHistory();
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralWorkoutHistory.json");
            writer.open();
            writer.write(wh);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralWorkoutHistory.json");
            wh = reader.read();
            checkGeneralWorkoutHistory(wh);
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    // EFFECTS: return WorkoutHistory with specific workouts
    private WorkoutHistory makeGeneralWorkoutHistory() {
        WorkoutHistory wh = new WorkoutHistory();

        Workout workout1 = new Workout("Leg Day");
        Workout workout2 = new Workout("Chest Day");

        Exercise squats = new Exercise("Squats");
        squats.addSet(6, 225);
        squats.addSet(3, 185);

        Exercise bench = new Exercise("Bench Press");
        bench.addSet(5, 315);

        Exercise flys = new Exercise("Cable Flys");
        flys.addSet(8, 60);
        flys.addSet(5, 50);

        workout1.addExercise(squats);
        workout2.addExercise(bench);
        workout2.addExercise(flys);

        wh.addWorkout(workout1);
        wh.addWorkout(workout2);
        return wh;
    }

}
