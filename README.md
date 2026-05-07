# Liftfolio

The aim of this project is to design a workout tracker application that allows users to log their workouts and view progress over time. Users will be able to record sessions consisting of multiple exercises and edit or remove those exercises. The application will also provide features such as history lookup, progression analysis for individual exercises, and graphical summaries that make it easy to visualize improvements across workout sessions.

This application is designed for:
- **Beginners** who want a simple, lightweight way to start logging workouts.
- **Experienced lifters** interested in monitoring long-term progress with graphs, and workout history.
- **Anyone** who prefers a local solution without ads or subscriptions.

This project interests me because most workout tracking apps I’ve tried include ads, subscriptions, or lock progress graphs behind paywalls. By developing this application, I hope to create a tool that could benefit others who share the same frustrations with existing fitness apps.

## User Stories
- As a user, I want to be able to add an exercise to a workout and specify the exercise name, sets, reps, and weight
- As a user, I want to be able to view a list of all exercises in a workout
- As a user, I want to be able to edit an exercise from a workout
- As a user, I want to be able to remove an exercise from a workout
- As a user, I want to have the option to save my workout history to a file
- As a user, I want to have the option to load my workout history from a file
- As a user, I want to be able to see my progress for a specific exercise over time displayed as a graph

## Instructions for End User

- You can view the panel that displays the Xs that have already been added to the Y by **viewing the left panel** to see workouts in workout history
- You can generate the first required action related to the user story "adding multiple Xs to a Y" by pressing **"Start Workout"** (bottom of left panel) and then **"Add Exercise"** (bottom of right panel)
- You can generate the second required action related to the user story "adding multiple Xs to a Y" by selecting a workout from the left panel and pressing the **"Remove Workout" button** (bottom of left panel)
- You can locate my visual component by looking at the **top-left of the application** for my logo
- You can save the state of my application by pressing the **"Save" button** found at the bottom of the left panel
- You can reload the state of my application by pressing the **"Load" button** found at the bottom of the left panel


## Phase 4: Task 2
Thu Nov 27 19:42:35 PST 2025
Legs workout has been added!

Thu Nov 27 19:42:56 PST 2025
Added 2 sets of Squats to Legs!

Thu Nov 27 19:43:08 PST 2025
Added 1 sets of Leg Press to Legs!

Thu Nov 27 19:43:21 PST 2025
ARmsssdf workout has been added!

Thu Nov 27 19:43:25 PST 2025
ARmsssdf workout has been removed.

Thu Nov 27 19:43:29 PST 2025
Arms workout has been added!

Thu Nov 27 19:43:44 PST 2025
Added 2 sets of Curls to Arms!

Thu Nov 27 19:43:50 PST 2025
Saved 2 workouts.


## Phase 4: Task 3

If I had more time to work on the project, I would refactor its design to incorporate the Observer Pattern. This would increase cohesion of the `WorkoutTrackerUI` class and decouple it from `WorkoutHistory`. My current design of `WorkoutTrackerUI` has low cohesion because it is responsible for manually syncing the frontend and backend. For example, `handleStartWorkout()` must call `workouts.add(workout)` and `workoutListModel.addElement(workout)` to make sure the data stays in sync. If one of these method calls is missed the data will be out of sync resulting in UI/data persistence bugs. 

To add the Observer Pattern, I would refactor `WorkoutHistory` to extend an Observable class and have `WorkoutTrackerUI` implement an Observer interface. Now the `handleStartWorkout()` method would only have to call `workouts.add(workout)`. The `WorkoutHistory` class would notify the UI when its state changes, and the UI would automatically react to this notification to update the frontend list. By removing the need for UI to explicitly manage the frontend list we can guarantee the UI will always be in sync with the model data. This refactor would also make it easier to add new frontend views in the future.
