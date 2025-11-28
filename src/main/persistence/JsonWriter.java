package persistence;

import model.Event;
import model.EventLog;
import model.WorkoutHistory;
import org.json.JSONObject;

import java.io.*;


// Represents a writer that writes JSON representation of workout history to file
// Structure of this class and methods were made by referencing JsonSerializationDemo
public class JsonWriter {
    private static int TAB = 4;
    private PrintWriter writer;
    private String destination;

    // EFFECTS: constructs writer to write to destionation file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    //          be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of workout history to file, and logs the event
    public void write(WorkoutHistory workouts) {
        JSONObject workoutsJson = workouts.toJson();
        saveToFile(workoutsJson.toString(TAB));
        EventLog.getInstance().logEvent(new Event("Saved " + workouts.getWorkouts().size() + " workouts to "
                                                  + destination));
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }
}
