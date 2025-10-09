package model;

// Represents an exercise set.
public class ExerciseSet {
    int reps;
    double weight;

    // REQUIRES: reps > 0, weight > 0, and weight in pounds (lbs)
    // EFFECTS: constructs an ExerciseSet object with the given reps and weight
    public ExerciseSet(int reps, double weight) {
        this.reps = reps;
        this.weight = weight;
    }

    // EFFECTS: return volume of the exercise set rounded to one decimal
    public double calculateVolume() {
        double volume = reps * weight;
        return Math.round(volume * 10.0) / 10.0;
    }

    // EFFECTS: return estimated one rep max rounded to one decimal
    public double estimateOneRepMax() {
        double orm = weight * (1 + reps/30.0);
        return Math.round(orm * 10.0) / 10.0;
    }

    public int getReps() {
        return this.reps;
    }

    public double getWeight() {
        return this.weight;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
