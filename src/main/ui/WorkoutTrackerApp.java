package ui;

import model.Exercise;
import model.ExerciseSet;
import model.Workout;
import model.WorkoutHistory;

import java.util.Scanner;

// Workout tracker application that allows the user to log and view workouts
// Structure of this class was made by referencing FlashcardReviewer from lab 4
public class WorkoutTrackerApp {
    private WorkoutHistory workouts;

    private Scanner scanner;
    private Boolean isApplicationRunning;

    // EFFECTS: instantiates WorkoutTrackerApp console UI application
    public WorkoutTrackerApp() {
        init();

        printDivider();
        System.out.println("Welcome to Liftfolio!");
        printDivider();

        while (this.isApplicationRunning) {
            handleMainMenu();
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes the application with starting values
    public void init() {
        this.workouts = new WorkoutHistory();
        this.scanner = new Scanner(System.in);
        this.isApplicationRunning = true;
    }

    // EFFECTS: displays and processes inputs for the main menu
    public void handleMainMenu() {
        displayMainMenu();
        String input = this.scanner.nextLine();
        processMainMenuCommands(input);
    }

    // EFFECTS: displays options that can be selected from main menu
    public void displayMainMenu() {
        System.out.println("Please choose an option:\n");
        System.out.println("1: Add Workout");
        System.out.println("2: View/Edit Workouts");
        System.out.println("3: Close Application");
        printDivider();
    }

    // EFFECTS: processes user input in the main menu
    public void processMainMenuCommands(String input) {
        printDivider();
        switch (input) {
            case "1":
                addNewWorkout();
                break;
            case "2":
                handleWorkoutHistoryMenu();
                break;
            case "3":
                quitApplication();
                break;
            default:
                System.out.println("Invalid input, please try again.");
        }
        printDivider();
    }

    // MODIFIES: this
    // EFFECTS: adds a new workout to workout history
    public void addNewWorkout() {
        System.out.println("Please enter a workout name:");
        String workoutName = this.scanner.nextLine();

        Workout workout = new Workout(workoutName);
        addExercise(workout);
        workouts.addWorkout(workout);
        System.out.println("Workout " + workout.getName() + " has been added!");
    }

    // MODIFIES: this
    // EFFECTS: prints a closing message and marks the program as not running
    public void quitApplication() {
        System.out.println("Thank you for using Liftfolio!");
        isApplicationRunning = false;
    }

    // EFFECTS: displays and processes inputs for the view workout history menu
    public void handleWorkoutHistoryMenu() {
        displayWorkoutHistoryMenu();
        String input = this.scanner.nextLine();
        processWorkoutHistoryMenuCommands(input);
    }

    // EFFECTS: displays all workouts and options that can be selected from the view
    // workout history menu
    public void displayWorkoutHistoryMenu() {
        int totalWorkouts = workouts.getWorkouts().size();

        System.out.println("Workout History\n");

        for (int i = 0; i < totalWorkouts; i++) {
            int workoutNum = i + 1;

            System.out.println("Workout " + workoutNum + ": " + workouts.getWorkout(i).getName());
        }

        System.out.println("Please choose an option:\n");
        System.out.println("Workout number to edit or remove");
        System.out.println("b: Return");
        printDivider();
    }

    // EFFECTS: processes user input in the view workout history menu
    public void processWorkoutHistoryMenuCommands(String input) {
        printDivider();
        if (input.equals("b")) {
            return;
        } else if (1 <= Integer.valueOf(input) && Integer.valueOf(input) <= workouts.getWorkouts().size()) {
            int index = Integer.valueOf(input) - 1;
            Workout selectedWorkout = workouts.getWorkout(index);
            handleWorkoutMenu(selectedWorkout);
        } else {
            System.out.println("Invalid input, please try again.");
        }
        printDivider();
    }

    // EFFECTS: displays and processes inputs for the view workout menu
    public void handleWorkoutMenu(Workout workout) {
        displayWorkoutMenu(workout);
        String input = this.scanner.nextLine();
        processWorkoutMenuCommands(input, workout);
    }

    // EFFECTS: displays exercises in workout and options that can be selected from
    // the view workout menu
    public void displayWorkoutMenu(Workout workout) {
        int totalExercises = workout.getExercises().size();

        System.out.println("Workout: " + workout.getName() + "\n");

        for (int i = 0; i < totalExercises; i++) {
            int exerciseNum = i + 1;

            System.out.println("Exercise " + exerciseNum + ": " + workout.getExercises().get(i).getName());
        }

        System.out.println("Please choose an option:\n");
        System.out.println("Exercise number to edit or remove");
        System.out.println("a: Add Exercise");
        System.out.println("n: Rename Workout");
        System.out.println("b: Return");
        printDivider();
    }

    // EFFECTS: processes user input in the view workout menu
    public void processWorkoutMenuCommands(String input, Workout workout) {
        printDivider();
        if (input.equals("b")) {
            return;
        } else if (input.equals("a")) {
            addExercise(workout);
        } else if (input.equals("n")) {
            renameWorkout(workout);
        } else if (1 <= Integer.valueOf(input) && Integer.valueOf(input) <= workout.getExercises().size()) {
            int index = Integer.valueOf(input) - 1;
            Exercise selectedExercise = workout.getExercises().get(index);
            handleExerciseMenu(selectedExercise, workout);
        } else {
            System.out.println("Invalid input, please try again.");
        }
        printDivider();
    }

    // MODIFIES: this
    // EFFECTS: adds a new exercise to workout
    public void addExercise(Workout workout) {
        Boolean continueAdding = true;

        while (continueAdding) {
            Exercise exercise = createExercise();
            workout.addExercise(exercise);
            System.out.println("Added " + exercise.getSets().size() + " sets of "
                    + exercise.getName() + " to " + workout.getName() + "!");

            System.out.println("Would you like to add another exercise to the workout? y/n:");
            String answer = this.scanner.nextLine();

            while (!answer.equals("y") && !(answer.equals("n"))) {
                System.out.println("Invalid input. Please enter 'y' or 'n'");
                answer = this.scanner.nextLine();
            }

            if (answer.equals("n")) {
                continueAdding = false;
            }
        }
    }

    // EFFECTS: return exercise created from user input
    public Exercise createExercise() {
        System.out.println("\nPlease enter an exercise name:");
        String exerciseName = this.scanner.nextLine();

        Exercise exercise = new Exercise(exerciseName);

        System.out.println("Please enter the number of sets:");
        int sets = Integer.valueOf(this.scanner.nextLine());

        for (int i = 1; i <= sets; i++) {
            System.out.println("Set-" + i + " Please enter reps:");
            int reps = Integer.valueOf(this.scanner.nextLine());

            System.out.println("Set-" + i + " Please enter weight:");
            int weight = Integer.valueOf(this.scanner.nextLine());

            exercise.addSet(reps, weight);
        }
        return exercise;
    }

    // MODIFIES: this, Workout
    // EFFECTS: renames the workout
    public void renameWorkout(Workout workout) {
        System.out.println("\nPlease enter a new workout name:");
        String workoutName = this.scanner.nextLine();

        workout.setName(workoutName);

        System.out.println("\n Workout name changed to " + workoutName);
    }

    // EFFECTS: displays and processes inputs for the exercise menu
    public void handleExerciseMenu(Exercise exercise, Workout workout) {
        displayExerciseMenu(exercise);
        String input = this.scanner.nextLine();
        processExerciseMenuCommands(input, exercise, workout);
    }

    // EFFECTS: displays exercise sets and options that can be selected from the
    // exercise menu
    public void displayExerciseMenu(Exercise exercise) {
        int totalSets = exercise.getSets().size();

        System.out.println("Exercise: " + exercise.getName() + "\n");

        for (int i = 0; i < totalSets; i++) {
            int setNum = i + 1;

            System.out.println("Set " + setNum + ": " + exercise.getSets().get(i).toString());
        }

        System.out.println("Please choose an option:\n");
        System.out.println("Set number to edit reps/weight");
        System.out.println("a: Add Set");
        System.out.println("n: Rename Exercise");
        System.out.println("r: Remove Exercise");
        System.out.println("b: Return");
        printDivider();
    }

    // EFFECTS: processes user input in the exercise menu
    public void processExerciseMenuCommands(String input, Exercise exercise, Workout workout) {
        printDivider();
        if (input.equals("b")) {
            return;
        } else if (input.equals("a")) {
            addSet(exercise);
        } else if (input.equals("r")) {
            removeExercise(input, exercise, workout);
        } else if (input.equals("n")) {
            renameExercise(exercise);
        } else if (1 <= Integer.valueOf(input) && Integer.valueOf(input) <= exercise.getSets().size()) {
            int index = Integer.valueOf(input) - 1;
            ExerciseSet selectedSet = exercise.getSets().get(index);
            editSet(selectedSet);
        } else {
            System.out.println("Invalid input, please try again.");
        }
        printDivider();
    }

    // MODIFIES: this, Exercise
    // EFFECTS: adds a new set to exercise
    public void addSet(Exercise exercise) {
        System.out.println("\nPlease enter reps:");
        int reps = Integer.valueOf(this.scanner.nextLine());

        System.out.println("\nPlease enter weight:");
        int weight = Integer.valueOf(this.scanner.nextLine());

        exercise.addSet(reps, weight);

        System.out.println("\nNew set with " + reps + " reps at " + weight + " has been added!");
    }

    // MODIFIES: this, ExerciseSet
    // EFFECTS: change the reps and weight of a set
    public void editSet(ExerciseSet set) {
        System.out.println("\nPlease enter reps:");
        int reps = Integer.valueOf(this.scanner.nextLine());

        System.out.println("\nPlease enter weight:");
        int weight = Integer.valueOf(this.scanner.nextLine());

        set.setReps(reps);
        set.setWeight(weight);

        System.out.println("\nSet has been updated!");
    }

    // MODIFIES: this, Exercise
    // EFFECTS: renames the exercise
    public void renameExercise(Exercise exercise) {
        System.out.println("\nPlease enter new name for this exercise:");
        String name = this.scanner.nextLine();

        exercise.setName(name);

        System.out.println("\nExercise has been renamed to " + name);
    }

    // MODIFIES: this, Exercise
    // EFFECTS: removes exercise from workout
    public void removeExercise(String input, Exercise exercise, Workout workout) {
        int index = Integer.valueOf(input);
        workout.removeExercise(index);

        System.out.println("\nExercise has been removed.");
    }

    // This method is from the flashcard reviewer lab
    // EFFECTS: prints a line of dashes to act as a divider
    private void printDivider() {
        System.out.println("----------------------------------------");
    }
}
