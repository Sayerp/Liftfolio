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
            checkGeneralWorkoutHistory(wh);
        } catch (Exception e) {
            fail("Couldn't read from file");
        }
    }
}
