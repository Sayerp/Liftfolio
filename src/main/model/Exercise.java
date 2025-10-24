package model;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import persistence.Writable;

// Represents an exercise with a name and sets.
public class Exercise implements Writable {
    private String name;
    private ArrayList<ExerciseSet> sets;

    // REQUIRES: string length > 0
    // EFFECTS: instantiates an exercise with given name and no sets
    public Exercise(String name) {
        this.name = name;
        sets = new ArrayList<>();
    }

    // REQUIRES: reps > 0, weight > 0, and weight in pounds (lbs)
    // MODIFIES: this
    // EFFECTS: adds a new set with the given reps and weight to sets
    public void addSet(int reps, double weight) {
        ExerciseSet set = new ExerciseSet(reps, weight);
        sets.add(set);
    }

    // REQUIRES: sets.size() > 0, and 0 <= index < sets.size()
    // MODIFIES: this
    // EFFECTS: removes set at given index position from sets
    public void removeSet(int index) {
        sets.remove(index);
    }

    // REQUIRES: sets.size() > 0, 0 <= index < sets.size(), reps > 0, weight > 0,
    //           and weight in pounds (lbs)
    // MODIFIES: ExerciseSet
    // EFFECTS: updates the reps and weight of set at given index position in sets
    public void editSet(int index, int reps, double weight) {
        ExerciseSet set = sets.get(index);
        set.setReps(reps);
        set.setWeight(weight);
    }

    // REQUIRES: sets.size() > 0
    // EFFECTS: return the sum of volume from each set in sets
    public double calculateTotalVolume() {
        double totalVolume = 0;

        for (ExerciseSet set : sets) {
            totalVolume += set.calculateVolume();
        }

        return totalVolume;
    }

    // REQUIRES: sets.size() > 0
    // EFFECTS: return the largest estimated one rep max in sets
    public double calculateOneRepMax() {
        double oneRepMax = 0;

        for (ExerciseSet set : sets) {
            if (set.estimateOneRepMax() > oneRepMax) {
                oneRepMax = set.estimateOneRepMax();
            }
        }

        return oneRepMax;
    }

    // REQUIRES: sets.size() > 0
    // EFFECTS: return the weighted average of weights from each set in sets
    //          rounded to one decimal
    //          totalVolume / totalReps
    public double calculateAverageWeight() {
        double totalVolume = 0;
        int totalReps = 0;

        for (ExerciseSet set : sets) {
            totalVolume += set.calculateVolume();
            totalReps += set.getReps();
        }

        return Math.round(totalVolume / totalReps * 10.0) / 10.0;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public ArrayList<ExerciseSet> getSets() {
        return this.sets;
    }

    @Override
    // EFFECTS: returns Exercise as a JSON containing name and array of sets
    public JSONObject toJson() {
        return new JSONObject();
    }

    // EFFECTS: returns sets in this exercise as a JSON array
    private JSONArray setsToJson() {
        return new JSONArray();
    }
}