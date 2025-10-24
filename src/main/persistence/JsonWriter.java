package persistence;

import model.WorkoutHistory;

import java.io.FileNotFoundException;

// Represents a writer that writes JSON representation of workout history to file
// Structure of this class and methods were made by referencing JsonSerializationDemo
public class JsonWriter {

    // EFFECTS: constructs writer to write to destionation file
    public JsonWriter(String destination) {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    //          be opened for writing
    public void open() throws FileNotFoundException {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of workout history to file
    public void write(WorkoutHistory workoutHistory) {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        // stub
    }
}
