package model;

import java.util.ArrayList;

// Represents a workout with a name and exercises
public class Workout {
    private String name;
    private ArrayList<Exercise> exercises;

    // REQUIRES: string length > 0
    // EFFECTS: instantiates a workout with given name, and and empty ArrayList of
    // Exercise
    public Workout(String name) {
        this.name = name;
        exercises = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds a new Exercise to exercises
    public void addExercise(Exercise exercise) {
        exercises.add(exercise);
    }

    // REQUIRES: exercises.size() > 0, and 0 <= index <= exercises.size()
    // MODIFIES: this
    // EFFECTS: removes Exercise at given index position from exercises
    public void removeExercise(int index) {
        exercises.remove(index);
    }

    // REQUIRES: exercises.size() > 0, 0 <= exerciseIndex <= exercises.size(),
    //           sets.size() of selected exercise > 0, 0 <= setIndex <= sets.size(), reps > 0,
    //           weight > 0, and weight in pounds (lbs)
    // MODIFIES: ExerciseSet
    // EFFECTS: updates reps and weight of ExerciseSet at setIndex for Exercise at
    //          exerciseIndex in exercises
    public void editExercise(int exerciseIndex, int setIndex, int reps, double weight) {
        exercises.get(exerciseIndex).editSet(setIndex, reps, weight);
    }

    // REQUIRES: exercises.size() > 0, 0 <= exerciseIndex <= exercises.size()
    // MODIFIES: Exercise
    // EFFECTS: updates name of Exercise at exerciseIndex in exercises
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
}
