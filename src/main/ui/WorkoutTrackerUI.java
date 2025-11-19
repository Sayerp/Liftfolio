package ui;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.io.FileNotFoundException;
import java.io.IOException;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;
import model.Exercise;
import model.ExerciseSet;
import model.Workout;
import model.WorkoutHistory;
import persistence.JsonReader;
import persistence.JsonWriter;

// This class was made by referencing SmartHomeUI from B1 practice, geeksforgeeks, and Java Swing documentation
// Represents the main application user interface for the workout tracker
@ExcludeFromJacocoGeneratedReport
public class WorkoutTrackerUI extends JFrame {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final String JSON_STORE = "./data/workouts.json";

    private WorkoutHistory workouts;
    private DefaultListModel<Workout> workoutListModel;
    private JList<Workout> workoutJList;
    private JPanel leftPanel;
    private JPanel rightPanel;

    private JButton startButton;
    private JButton removeButton;
    private JButton saveButton;
    private JButton loadButton;
    private JButton addExerciseButton;

    private JLabel workoutNameLabel;
    private JTextArea workoutInfo;

    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // MODIFIES: this
    // EFFECTS: creates WorkoutTrackerUI, loads workouts, displays sidebar and main panel
    protected WorkoutTrackerUI() {
        super("LiftFolio");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        this.jsonWriter = new JsonWriter(JSON_STORE);
        this.jsonReader = new JsonReader(JSON_STORE);
        workouts = new WorkoutHistory();
        setupPanels();
        loadWorkoutList();
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: creates split planel to hold left and right panels
    private void setupPanels() {
        leftPanel = createLeftPanel();
        rightPanel = createRightPanel();

        JSplitPane splitPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
        splitPanel.setDividerLocation(300);
        add(splitPanel, BorderLayout.CENTER);
    }

    // MODIFIES: this
    // EFFECTS: return panel with logo at top, scrollable list of workouts in center, and
    //          buttons for save, load, start new workout, remove workout at bottom 
    private JPanel createLeftPanel() {
        JPanel leftPanel = new JPanel(new BorderLayout());

        JLabel logoLabel = new JLabel(new ImageIcon("./data/logo.png"));
        logoLabel.setHorizontalAlignment(JLabel.CENTER);
        leftPanel.add(logoLabel, BorderLayout.NORTH);

        workoutListModel = new DefaultListModel<>();
        workoutJList = new JList<>(workoutListModel);
        leftPanel.add(new JScrollPane(workoutJList), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new GridLayout(2, 2));
        startButton = new JButton("Start Workout");
        removeButton = new JButton("Remove Workout");
        saveButton = new JButton("Save");
        loadButton = new JButton("Load");

        buttonPanel.add(startButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(loadButton);
        leftPanel.add(buttonPanel, BorderLayout.SOUTH);

        startButton.addActionListener(e -> handleStartWorkout());
        removeButton.addActionListener(e -> handleRemoveWorkout());
        saveButton.addActionListener(e -> handleSave());
        loadButton.addActionListener(e -> handleLoad());

        workoutJList.addListSelectionListener(e -> handleSelectedWorkout());

        return leftPanel;
    }

    // MODIFIES: this
    // EFFECTS: return panel with workout name label at top, and scrollable workout info in center 
    private JPanel createRightPanel() {
        JPanel rightPanel = new JPanel(new BorderLayout());

        workoutNameLabel = new JLabel("Select or start a workout");
        workoutNameLabel.setHorizontalAlignment(JLabel.CENTER);
        workoutNameLabel.setFont(new Font("Arial", Font.BOLD, 18));
        rightPanel.add(workoutNameLabel, BorderLayout.NORTH);

        workoutInfo = new JTextArea();
        workoutInfo.setEditable(false);
        workoutInfo.setLineWrap(true);
        workoutInfo.setWrapStyleWord(true);
        workoutInfo.setMargin(new Insets(10, 10, 10, 10));
        workoutInfo.setFont(new Font("Arial", Font.BOLD, 12));
        rightPanel.add(new JScrollPane(workoutInfo), BorderLayout.CENTER);

        addExerciseButton = new JButton("Add Exercise");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addExerciseButton);
        rightPanel.add(buttonPanel, BorderLayout.SOUTH);

        addExerciseButton.addActionListener(e -> handleAddExercise());

        return rightPanel;
    }

    // MODIFIES: this
    // EFFECTS: load workouts from workouts model to DefaultListModel
    private void loadWorkoutList() {
        workoutListModel.clear();
        workoutListModel.addAll(workouts.getWorkouts());
    }

    // EFFECTS: saves current state of workouts to json file, displays error dialog if save unsuccessful 
    private void handleSave() {
        try {
            jsonWriter.open();
            jsonWriter.write(workouts);
            jsonWriter.close();
            System.out.println("Saved " + workouts.getWorkouts().size() + " workouts to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads workouts from json file, updates UI list of workouts, displays error if load unsuccessful
    private void handleLoad() {
        try {
            workouts = jsonReader.read();
            System.out.println("Loaded from json");
            loadWorkoutList();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Unable to read from file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: creates new workout with user inputted name, adds workout to workouts model and workoutListModel view,
    //          and selects the workout from left panel list
    private void handleStartWorkout() {
        String name = JOptionPane.showInputDialog(this, "Enter workout name:");

        if (name != null && !name.trim().isEmpty()) {
            Workout workout = new Workout(name);
            workouts.addWorkout(workout);
            workoutListModel.addElement(workout);
            workoutJList.setSelectedValue(workout, true);
        }
    }

    // MODIFIES: this
    // EFFECTS: if workout was selected remove from workouts model and workoutListModel view, else do nothing
    private void handleRemoveWorkout() {
        int workoutIndex = workoutJList.getSelectedIndex();

        if (workoutIndex != -1) {
            workouts.removeWorkout(workoutIndex);
            workoutListModel.remove(workoutIndex);
            updateInfoPanel(null);
        }
    }

    // MODIFIES: this
    // EFFECTS: get selected workout and display information in right info panel
    private void handleSelectedWorkout() {
        Workout workout = workoutJList.getSelectedValue();
        updateInfoPanel(workout);
    }

    // MODIFIES: this
    // EFFECTS: if workout not selected show pop-up error, else create exercise with user prompted user name,
    //          reps/weights per set, add exercise to workouts model, and display in right info panel.
    //          Display error dialog if invalid number entered.
    private void handleAddExercise() {
        Workout workout = workoutJList.getSelectedValue();
        if (workout == null) {
            JOptionPane.showMessageDialog(this, "Select a workout first.");
            return;
        }

        String name = JOptionPane.showInputDialog(this, "Exercise Name:");
        if (name == null || !name.trim().isEmpty()) {
            return;
        }

        String setsString = JOptionPane.showInputDialog(this, "Number of Sets:");
        if (setsString == null) {
            return;
        }

        try {
            int numSets = Integer.parseInt(setsString);
            Exercise exercise = new Exercise(name);
            addSetsToExercise(workout, exercise, numSets);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid number. Exercise Cancelled");
        }
    }

    // MODIFIES: this, workout, exercise
    // EFFECTS: loop prompt for reps/weight numSets times adding each set to exercise, then add exercise
    //          to workout, and update UI
    //          Throws NumberFormatException if input is invalid 
    private void addSetsToExercise(Workout workout, Exercise exercise, int numSets) throws NumberFormatException {
        for (int i = 1; i <= numSets; i++) {
            String repString = JOptionPane.showInputDialog(this, "Set " + i + " Reps:");

            if (repString == null) {
                return;
            }
            int reps = Integer.parseInt(repString);

            String weightString = JOptionPane.showInputDialog(this, "Set " + i + " Weight (lbs):");
            if (weightString == null) {
                return;
            }
            double weight = Double.parseDouble(weightString);

            exercise.addSet(reps, weight);
        }

        workout.addExercise(exercise);
        updateInfoPanel(workout);
    }

    // MODIFIES: this
    // EFFECTS: if workout not selected display default message, else set right panel label with workout name,
    //          display rows for each exercise in center, and scrolls to top
    private void updateInfoPanel(Workout workout) {
        if (workout == null) {
            workoutNameLabel.setText("Select or start a workout");
            workoutInfo.setText("");
            return;
        }

        workoutNameLabel.setText(workout.getName());
        String workoutDetail = "";

        for (Exercise exercise : workout.getExercises()) {
            workoutDetail += exercise.getName() + ": ";
            for (ExerciseSet set : exercise.getSets()) {
                workoutDetail += set.toString() + " | ";
            }
            if (!exercise.getSets().isEmpty()) {
                workoutDetail = workoutDetail.substring(0, workoutDetail.length() - 3);
            }
            workoutDetail += "\n\n";
        }

        workoutInfo.setText(workoutDetail);
        workoutInfo.setCaretPosition(0);
    }
}
