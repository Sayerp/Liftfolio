package model;

import java.util.ArrayList;

// Represents an exercise.
public class Exercise {

    // REQUIRES: string length > 0
    // EFFECTS: instantiates an exercise with given name, and an empty ArrayList of
    // ExerciseSet
    public Exercise(String name) {
        // stub
    }

    // REQUIRES: reps > 0, weight > 0, and weight in pounds (lbs)
    // MODIFIES: this
    // EFFECTS: adds a new ExerciseSet with the given reps and weight to sets
    public void addSet(int reps, double weight) {
        // stub
    }

    // REQUIRES: sets.size() > 0, and 0 <= index <= sets.size()
    // MODIFIES: this
    // EFFECTS: removes ExerciseSet at given index position from sets
    public void removeSet(int index) {
        // stub
    }

    // REQUIRES: sets.size() > 0, 0 <= index <= sets.size(), reps > 0, weight > 0,
    // and weight in pounds (lbs)
    // MODIFIES: ExerciseSet
    // EFFECTS: updates the reps and weight of ExerciseSet at given index position
    // in sets
    public void editSet(int index, int reps, double weight) {
        // stub
    }

    // REQUIRES: sets.size() > 0
    // EFFECTS: return the sum of volume from each ExerciseSet in sets
    public double calculateTotalVolume() {
        return 0; // stub
    }

    // REQUIRES: sets.size() > 0
    // EFFECTS: return the largest estimated one rep max in sets
    public double calculateOneRepMax() {
        return 0; // stub
    }

    // REQUIRES: sets.size() > 0
    // EFFECTS: return the weighted average of weights from each ExerciseSet in sets
    //          rounded to one decimal
    //          totalVolume / totalReps
    public double calculateAverageWeight() {
        return 0; // stub
    }

    public void setName(String name) {
        // stub
    }

    public String getName() {
        return ""; // stub
    }

    public ArrayList<ExerciseSet> getSets() {
        return null; // stub
    }
}