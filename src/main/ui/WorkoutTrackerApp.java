package ui;

import model.Exercise;
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
        Boolean continueAdding = true;
        
        System.out.println("Please enter a workout name:");
        String workoutName = this.scanner.nextLine();
        Workout workout = new Workout(workoutName);

        while (continueAdding) {
            System.out.println("\nPlease enter an exercise name:");
            String exerciseName = this.scanner.nextLine();

            Exercise exercise = new Exercise(exerciseName);

            System.out.println("\nPlease enter the number of sets:");
            int sets = Integer.valueOf(this.scanner.nextLine());

            for (int i=1; i <= sets; i++) {
                System.out.println("\n Set-" + i + " Please enter reps:");
                int reps = Integer.valueOf(this.scanner.nextLine());

                System.out.println("\n Set-" + i + " Please enter weight:");
                int weight = Integer.valueOf(this.scanner.nextLine());

                exercise.addSet(reps, weight);
            }
            
            workout.addExercise(exercise);
            System.out.println("Added " + sets + " sets of " + exerciseName + " to " + workoutName + " workout!");

            System.out.println("Would you like to add another exercise to the workout? y/n:");
            String answer = this.scanner.nextLine();

            while (!answer.equals("y") && !(answer.equals("n"))) {
                System.out.println("Invalid input. Please enter 'y' or 'n'");
                answer = this.scanner.nextLine();
            }

            if (answer.equals("n")) {
                workouts.addWorkout(workout);
                continueAdding = false;
            }
        }
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
    //          workout history menu
    public void displayWorkoutHistoryMenu() {
        int totalWorkouts = workouts.getWorkouts().size();

        System.out.println("Workout History\n");

        for (int i=0; i < totalWorkouts; i++) {
            int workoutNum = i + 1;

            System.out.println("Workout " + workoutNum + ": " + workouts.getWorkout(i).getName());
        }

        System.out.println("\nEnter a workout number to edit or remove, or 'b' to return");
        printDivider();
    }

    // EFFECTS: processes user input in the view workout history menu
    public void processWorkoutHistoryMenuCommands(String input) {
        printDivider();
        if (input.equals("b")) {
            return;
        }
        else if (1 <= Integer.valueOf(input) && Integer.valueOf(input) <= workouts.getWorkouts().size()) {
            handleWorkoutMenu();
        } else {
            System.out.println("Invalid input, please try again.");
        }
        printDivider();
    }

    // EFFECTS: displays and processes inputs for the view workout menu
    public void handleWorkoutMenu() {
        // stub
    }

    // EFFECTS: displays exercises in workout and options that can be selected from
    //          the view workout menu
    public void displayWorkoutMenu() {
        // stub
    }

    // EFFECTS: displays all exercises in workout one at a time
    public void displayWorkout() {
        // stub
    }

    // EFFECTS: processes user input in the view workout menu
    public void processWorkoutMenuCommands(String input) {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: adds a new exercise to workout
    public void addExercise() {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: renames the workout
    public void renameWorkout() {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: removes workout from workout history
    public void removeWorkout() {
        // stub
    }

    // EFFECTS: displays and processes inputs for the exercise menu
    public void handleExerciseMenu() {
        // stub
    }

    // EFFECTS: displays exercise sets and options that can be selected from the
    //          exercise menu
    public void displayExerciseMenu() {
        // stub
    }

    // EFFECTS: displays all sets of an exercise one at a time
    public void displayExercise() {
        // stub
    }

    // EFFECTS: processes user input in the exercise menu
    public void processExerciseMenuCommands(String input) {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: adds a new set to exercise
    public void addSet() {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: change the reps and weight of a set
    public void editSet() {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: renames the exercise
    public void renameExercise() {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: removes set from exercise
    public void removeSet() {
        // stub
    }

    // This method is from the flashcard reviewer lab
    // EFFECTS: prints a line of dashes to act as a divider
    private void printDivider() {
        System.out.println("----------------------------------------");
    }
}
