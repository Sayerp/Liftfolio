package persistence;

import model.Exercise;
import model.ExerciseSet;
import model.Workout;
import model.WorkoutHistory;

import java.io.IOException;

import org.json.*;

// Represents a reader that reads workout history from JSON data stored in file
// Structure of this class was made by referencing JsonSerializationDemo
public class JsonReader {

    // EFFECTS: constructs a reader that reads from a source file
    public JsonReader(String source) {
        // stub
    }

    // EFFECTS: reads workout histroy from file and returns it;
    //          throws IOException if an error occurs reading data from file
    public WorkoutHistory read() throws IOException {
        return new WorkoutHistory(); // stub
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        return null; // stub
    }

    // REQUIRES: workoutsJson contains JSONArray for the "workouts" key
    // EFFECTS: returns workout history with workouts parsed from workoutsJson
    private WorkoutHistory parseWorkoutHistory(JSONObject workoutsJson) {
        return new WorkoutHistory(); // stub
    }

    // REQUIRES: workoutsJson contains JSONArray for the "workouts" key
    // MODIFIES: workouts
    // EFFECTS: adds every workout in workoutsJson to workouts
    private void addWorkouts(WorkoutHistory workouts, JSONObject workoutsJson) {
        // stub
    }

    // REQUIRES: workoutJson contains JSONArray for the "exercises" key
    // EFFECTS: returns workout with exercises parsed from workoutJson
    private Workout parseWorkout(JSONObject workoutJson) {
        return new Workout(""); // stub
    }

    // REQUIRES: workoutJson contains JSONArray for the "exercises" key
    // MODIFIES: workout
    // EFFECTS: add every exercise from workoutJson to workout
    private void addExercises(Workout workout, JSONObject workoutJson) {
        // stub
    }

    // REQUIRES: exerciseJson contains JSONArray for the "sets" key
    // EFFECTS: returns exercise with sets parsed from exerciseJson
    private Exercise parseExercise(JSONObject exerciseJson) {
        return new Exercise(""); // stub
    }

    // REQUIRES: exerciseJson contains JSONArray for the "sets" key
    // MODIFIES: exercise
    // EFFECTS: add every set from exerciseJson to exercise
    private void addExerciseSet(Exercise exercise, JSONObject exerciseJson) {
        // stub
    }

    // REQUIRES: setJson contains "reps" and "weight" key
    // EFFECTS: returns set with reps and weight parsed from setJson
    private ExerciseSet parseExerciseSet(JSONObject setJson) {
        return new ExerciseSet(0, 0); // stub
    }
}
