package model;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import persistence.Writable;

// Represents a collection of all workouts
public class WorkoutHistory implements Writable {
    private ArrayList<Workout> workouts;

    // EFFECTS: creates a new instance of a workout history with no workouts
    public WorkoutHistory() {
        workouts = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds given workout to workouts, and logs the event
    public void addWorkout(Workout workout) {
        workouts.add(workout);
        EventLog.getInstance().logEvent(new Event(workout.getName() + " workout has been added!"));
    }

    // REQUIRES: workouts.size() > 0, and 0 <= index < workouts.size()
    // MODIFIES: this
    // EFFECTS: removes workout at given index position from workouts, and logs the event
    public void removeWorkout(int index) {
        workouts.remove(index);
        EventLog.getInstance().logEvent(new Event(workouts.get(index).getName() + " workout has been removed."));
    }

    // REQUIRES: workouts.size() > 0, and 0 <= index < workouts.size()
    // MODIFIES: Workout
    // EFFECTS: updates name of workout at given index in workouts
    public void renameWorkout(int index, String name) {
        workouts.get(index).setName(name);
    }

    public ArrayList<Workout> getWorkouts() {
        return workouts;
    }

    public Workout getWorkout(int index) {
        return workouts.get(index);
    }

    @Override
    // EFFECTS: returns WorkoutHistory as a JSON containing an array of workouts, and logs event
    public JSONObject toJson() {
        JSONObject workoutsJson = new JSONObject();
        workoutsJson.put("workouts", workoutsToJson());
        EventLog.getInstance().logEvent(new Event("Saved " + workouts.size() + " workouts."));
        return workoutsJson;
    }

    // EFFECTS: returns workouts in this workout history as a JSON array
    private JSONArray workoutsToJson() {
        JSONArray workoutsArray = new JSONArray();

        for (Workout workout : workouts) {
            workoutsArray.put(workout.toJson());
        }

        return workoutsArray;
    }
}
