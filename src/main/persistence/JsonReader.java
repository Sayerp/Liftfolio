package persistence;

import model.Exercise;
import model.ExerciseSet;
import model.Workout;
import model.WorkoutHistory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads workout history from JSON data stored in file
// Structure of this class was made by referencing JsonSerializationDemo
public class JsonReader {
    private String source;

    // EFFECTS: constructs a reader that reads from a source file
    public JsonReader(String source) {
        this.source = source;
    }

    // read() method is from JsonSerializationDemo
    // EFFECTS: reads workout histroy from file and returns it;
    //          throws IOException if an error occurs reading data from file
    public WorkoutHistory read() throws IOException {
        String jsonData = readFile(source);
        JSONObject workoutsJson = new JSONObject(jsonData);
        return parseWorkoutHistory(workoutsJson);
    }

    // readFile() method is from JsonSerializationDemo
    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // REQUIRES: workoutsJson contains JSONArray for the "workouts" key
    // EFFECTS: returns workout history with workouts parsed from workoutsJson
    private WorkoutHistory parseWorkoutHistory(JSONObject workoutsJson) {
        WorkoutHistory workouts = new WorkoutHistory();
        addWorkouts(workouts, workoutsJson);
        return workouts;
    }

    // REQUIRES: workoutsJson contains JSONArray for the "workouts" key
    // MODIFIES: workouts
    // EFFECTS: adds every workout in workoutsJson to workouts
    private void addWorkouts(WorkoutHistory workouts, JSONObject workoutsJson) {
        JSONArray jsonArray = workoutsJson.getJSONArray("workouts");
        for (Object json : jsonArray) {
            JSONObject nextWorkout = (JSONObject) json;
            Workout workout = parseWorkout(nextWorkout);
            workouts.addWorkout(workout);
        }
    }

    // REQUIRES: workoutJson contains JSONArray for the "exercises" key
    // EFFECTS: returns workout with exercises parsed from workoutJson
    private Workout parseWorkout(JSONObject workoutJson) {
        String name = workoutJson.getString("name");
        Workout workout = new Workout(name);
        addExercises(workout, workoutJson);
        return workout;
    }

    // REQUIRES: workoutJson contains JSONArray for the "exercises" key
    // MODIFIES: workout
    // EFFECTS: add every exercise from workoutJson to workout
    private void addExercises(Workout workout, JSONObject workoutJson) {
        JSONArray jsonArray = workoutJson.getJSONArray("exercises");
        for (Object json : jsonArray) {
            JSONObject nextExercise = (JSONObject) json;
            Exercise exercise = parseExercise(nextExercise);
            workout.addExercise(exercise);
        }
    }

    // REQUIRES: exerciseJson contains JSONArray for the "sets" key
    // EFFECTS: returns exercise with sets parsed from exerciseJson
    private Exercise parseExercise(JSONObject exerciseJson) {
        String name = exerciseJson.getString("name");
        Exercise exercise = new Exercise(name);
        addExerciseSet(exercise, exerciseJson);
        return exercise;
    }

    // REQUIRES: exerciseJson contains JSONArray for the "sets" key
    // MODIFIES: exercise
    // EFFECTS: add every set from exerciseJson to exercise
    private void addExerciseSet(Exercise exercise, JSONObject exerciseJson) {
        JSONArray jsonArray = exerciseJson.getJSONArray("sets");
        for (Object json : jsonArray) {
            JSONObject nextSet = (JSONObject) json;
            ExerciseSet set = parseExerciseSet(nextSet);
            exercise.addSet(set.getReps(), set.getWeight());
        }
    }

    // REQUIRES: setJson contains "reps" and "weight" key
    // EFFECTS: returns set with reps and weight parsed from setJson
    private ExerciseSet parseExerciseSet(JSONObject setJson) {
        int reps = setJson.getInt("reps");
        double weight = setJson.getDouble("weight");
        ExerciseSet set = new ExerciseSet(reps, weight);
        return set;
    }
}
