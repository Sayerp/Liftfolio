package ui;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

// Represents entry point for the Workout Tracker application
@ExcludeFromJacocoGeneratedReport
public class Main {
    // EFFECTS: creates an instance of the WorkoutTracker class
    public static void main(String[] args) throws Exception {
        new WorkoutTrackerUI();
        new WorkoutTrackerApp();
    }
}
