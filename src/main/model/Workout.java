package model;

import java.util.ArrayList;

// Represents a workout with a name and exercises
public class Workout {

    // REQUIRES: string length > 0
    // EFFECTS: instantiates a workout with given name, and and empty ArrayList of
    // Exercise
    public Workout(String name) {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: adds a new Exercise with to exercises
    public void addExercise(Exercise exercise) {
        // stub
    }

    // REQUIRES: exercises.size() > 0, and 0 <= index <= exercises.size()
    // MODIFIES: this
    // EFFECTS: removes Exercise at given index position from exercises
    public void removeExercise(int index) {
        // stub
    }

    // REQUIRES: exercises.size() > 0, 0 <= exerciseIndex <= exercises.size(),
    //           sets.size() of selected exercise > 0, 0 <= setIndex <= sets.size(), reps > 0,
    //           weight > 0, and weight in pounds (lbs)
    // MODIFIES: ExerciseSet
    // EFFECTS: updates reps and weight of ExerciseSet at setIndex for Exercise at
    //          exerciseIndex in exercises
    public void editExercise(int exerciseIndex, int setIndex, int reps, double weight) {
        // stub
    }

    // REQUIRES: exercises.size() > 0, 0 <= exerciseIndex <= exercises.size()
    // MODIFIES: Exercise
    // EFFECTS: updates name of Exercise at exerciseIndex in exercises
    public void editExercise(int exerciseIndex, String name) {
        // stub
    }

    public void setName(String name) {
        // stub
    }

    public ArrayList<Exercise> getExercises() {
        return null; // stub
    }

    public String getName() {
        return "";
    }
}
