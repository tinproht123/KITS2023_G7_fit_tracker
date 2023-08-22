package com.example.fittracker.controller;

import com.example.fittracker.model.Goal;
import com.example.fittracker.model.Report;
import com.example.fittracker.model.User;
import com.example.fittracker.model.Workout;
import com.example.fittracker.service.GoalService;
import com.example.fittracker.service.ReportService;
import com.example.fittracker.service.UserService;
import com.example.fittracker.service.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private ReportService reportService;
    @Autowired
    private GoalService goalService;
    @Autowired
    private WorkoutService workoutService;

    // user

        @GetMapping("/user")
        public ResponseEntity<List<User>> getAllUsers() {
            List<User> users = userService.getAllUsers();
            return new ResponseEntity<>(users, HttpStatus.OK);
        }
//        @PostMapping("/user")
//        public ResponseEntity<User> createUser(@RequestBody User user) {
//            User savedUser = userService.saveUser(user);
//            return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
//        }
@PostMapping(value = "/user/add", consumes = "multipart/form-data")
public ResponseEntity<User> saveUser(@ModelAttribute User user, @RequestParam("imageFile") MultipartFile imageFile) throws IOException {
    User savedBook = userService.saveUser(user, imageFile);
    return ResponseEntity.ok(savedBook);
}
        @GetMapping("/user/{id}")
        public ResponseEntity<User> getUserById(@PathVariable Long id) {
            User user = userService.getUserById(id);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        @PutMapping("/user/{id}")
        public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
            User updatedUser = userService.updateUser(id, userDetails);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        }
        @DeleteMapping("/user/{id}")
        public ResponseEntity<HttpStatus> deleteUser(@PathVariable Long id) {
            userService.deleteUser(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

    // goal

        @GetMapping("/goal")
        public ResponseEntity<List<Goal>> getAllGoals() {
            List<Goal> goals = goalService.getAllGoals();
            return new ResponseEntity<>(goals, HttpStatus.OK);
        }
        @PostMapping("/goal")
        public ResponseEntity<Goal> createGoal(@RequestBody Goal goal) {
            Goal savedGoal = goalService.saveGoal(goal);
            return new ResponseEntity<>(savedGoal, HttpStatus.CREATED);
        }
        @GetMapping("/goal/{id}")
        public ResponseEntity<Goal> getGoalById(@PathVariable Long id) {
            Goal goal = goalService.getGoalById(id);
            return new ResponseEntity<>(goal, HttpStatus.OK);
        }
        @DeleteMapping("/goal/{id}")
        public ResponseEntity<HttpStatus> deleteGoal(@PathVariable Long id) {
            goalService.deleteGoal(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

    // workout

        @GetMapping("/workout")
        public ResponseEntity<List<Workout>> getAllWorkouts() {
            List<Workout> workouts = workoutService.getAllWorkouts();
            return new ResponseEntity<>(workouts, HttpStatus.OK);
        }
        @PostMapping("/workout")
        public ResponseEntity<Workout> createWorkout(@RequestBody Workout workout) {
            Workout savedWorkout = workoutService.saveWorkout(workout);
            return new ResponseEntity<>(savedWorkout, HttpStatus.CREATED);
        }
        @GetMapping("/workout/{id}")
        public ResponseEntity<Workout> getWorkoutById(@PathVariable Long id) {
            Workout workout = workoutService.getWorkoutById(id);
            return new ResponseEntity<>(workout, HttpStatus.OK);
        }
        @PutMapping("/workout/{id}")
        public ResponseEntity<Workout> updateWorkout(@PathVariable Long id, @RequestBody Workout workoutDetails) {
            Workout updatedWorkout = workoutService.updateWorkout(id, workoutDetails);
            return new ResponseEntity<>(updatedWorkout, HttpStatus.OK);
        }
        @DeleteMapping("/workout/{id}")
        public ResponseEntity<HttpStatus> deleteWorkout(@PathVariable Long id) {
            workoutService.deleteWorkout(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        // workout-advanced
        @GetMapping("/workout/user/{userId}")
        public ResponseEntity<List<Workout>> getWorkoutsByUserId(@PathVariable("userId") Long userId) {
            List<Workout> workouts = workoutService.getWorkoutsByUserId(userId);
            return ResponseEntity.ok(workouts);
        }
        @GetMapping("/workout/{year}/{month}")
        public ResponseEntity<List<Workout>> getAllWorkoutsByMonthAndYear(@PathVariable("year") int year, @PathVariable("month") int month) {
            try {
                List<Workout> workouts = workoutService.getAllWorkoutsByMonthAndYear(year, month);
                return ResponseEntity.ok(workouts);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }


    // report

        @PostMapping("/report")
        public ResponseEntity<Report> createReport(@RequestBody Report report) {
            Report savedReport = reportService.saveReport(report);
            return new ResponseEntity<>(savedReport, HttpStatus.CREATED);
        }

    // post

}
