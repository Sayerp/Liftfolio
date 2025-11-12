package ui;

import javax.swing.*;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

// This class was made by referencing SmartHomeUI from B1 practice
// Represents the main application user interface for the workout tracker
@ExcludeFromJacocoGeneratedReport
public class WorkoutTrackerUI extends JFrame {

    // MODIFIES: this
    // EFFECTS: creates WorkoutTrackerUI, loads workouts, displays sidebar and main panel
    protected WorkoutTrackerUI() {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: creates split planel to hold left and right panels
    private void setupPanels() {
        // void
    }

    // MODIFIES: this
    // EFFECTS: return panel with logo at top, scrollable list of workouts in center, and
    //          buttons for save, load, start new workout, remove workout at bottom 
    private JPanel createLeftPanel() {
        return new JPanel();
    }

    // MODIFIES: this
    // EFFECTS: return panel with workout name label at top, and scrollable workout info in center 
    private JPanel createRightPanel() {
        return new JPanel();
    }

    // MODIFIES: this
    // EFFECTS: load workouts from workouts model to DefaultListModel
    private void loadWorkoutList() {
        // stub
    }

    // EFFECTS: saves current state of workouts to json file and displays success/error panel 
    private void handleSave() {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: loads workouts from json file, updates UI list of workouts, and displays success/error panel
    private void handleLoad() {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: creates new workout with user inputted name, adds workout to workouts model and workoutListModel view,
    //          and selects the workout from left panel list
    private void handleStartWorkout() {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: if workout was selected remove from workouts model and workoutListModel view, else do nothin
    private void handleDeleteWorkout() {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: get selected workout and display information in right info panel
    private void handleSelectedWorkout() {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: if workout not selected do nothing, else create exercise with user prompted user name,
    //          reps/weights per set, add exercise to workouts model, and display in right info panel
    private void handleAddExercise() {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: if workout not selected display default message, else set right panel label with workout name,
    //          display rows for each exercise in center, and scrolls to top
    private void updateInfoPanel() {
        // stub
    }
}
