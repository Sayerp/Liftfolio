package model;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import persistence.Writable;

// Represents a workout with a name and exercises
public class Workout implements Writable {
    private String name;
    private ArrayList<Exercise> exercises;

    // REQUIRES: string length > 0
    // EFFECTS: instantiates a workout with given name, and no exercises
    public Workout(String name) {
        this.name = name;
        exercises = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds an exercise to exercises
    public void addExercise(Exercise exercise) {
        exercises.add(exercise);
    }

    // REQUIRES: exercises.size() > 0, and 0 <= index < exercises.size()
    // MODIFIES: this
    // EFFECTS: removes exercise at given index position from exercises
    public void removeExercise(int index) {
        exercises.remove(index);
    }

    // REQUIRES: exercises.size() > 0, 0 <= exerciseIndex < exercises.size(),
    //           sets.size() of selected exercise > 0, 0 <= setIndex < sets.size(), reps > 0,
    //           weight > 0, and weight in pounds (lbs)
    // MODIFIES: ExerciseSet
    // EFFECTS: updates reps and weight of a set at setIndex for exercise at
    //          exerciseIndex in exercises
    public void editExercise(int exerciseIndex, int setIndex, int reps, double weight) {
        exercises.get(exerciseIndex).editSet(setIndex, reps, weight);
    }

    // REQUIRES: exercises.size() > 0, 0 <= exerciseIndex < exercises.size()
    // MODIFIES: Exercise
    // EFFECTS: updates name of exercise at exerciseIndex in exercises
    public void editExercise(int exerciseIndex, String name) {
        exercises.get(exerciseIndex).setName(name);
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Exercise> getExercises() {
        return exercises;
    }

    public String getName() {
        return this.name;
    }

    @Override
    // EFFECTS: returns Workout as a JSON containing name and array of exercises
    public JSONObject toJson() {
        JSONObject workoutJson = new JSONObject();
        workoutJson.put("name", name);
        workoutJson.put("exercises", exercisesToJson());
        return workoutJson;
    }

    // EFFECTS: returns exercises in this workout as a JSON array
    private JSONArray exercisesToJson() {
        JSONArray exercisesArray = new JSONArray();

        for (Exercise exercise : exercises) {
            exercisesArray.put(exercise.toJson());
        }

        return exercisesArray;
    }

    // EFFECTS: return name of workout as string representation of workout
    @Override
    public String toString() {
        return this.name;
    }
}
