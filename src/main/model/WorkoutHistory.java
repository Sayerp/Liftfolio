package model;

import java.util.ArrayList;

// Represents a collection of all workouts
public class WorkoutHistory {
    private ArrayList<Workout> workouts;

    // EFFECTS: creates a new instance of a workout history with no workouts
    public WorkoutHistory() {
        workouts = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds given workout to workouts
    public void addWorkout(Workout workout) {
        workouts.add(workout);
    }

    // REQUIRES: workouts.size() > 0, and 0 <= index <= workouts.size()
    // MODIFIES: this
    // EFFECTS: removes workout at given index position from workouts
    public void removeWorkout(int index) {
        workouts.remove(index);
    }

    public ArrayList<Workout> getWorkouts() {
        return workouts;
    }

    public Workout getWorkout(int index) {
        return workouts.get(index);
    }
}
