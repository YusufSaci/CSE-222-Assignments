package MyTaskManager;

import MyPriorityQueue.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Manages users and their tasks using a priority queue.
 * Tasks are processed in order of user priority and task ID.
 */
public class TaskManager {

    private ArrayList<User> users;                  // List of users loaded from config
    private MyPriorityQueue<Task> taskQueue;        // Min-heap priority queue of tasks
    private int nextTaskID;                         // Auto-incrementing task ID

    /**
     * Constructs a TaskManager with users loaded from a configuration file.
     *
     * @param configPath path to the configuration file containing user priorities
     * Time complexity: O(n), where n is the number of users
     */
    public TaskManager(String configPath) {
        users = new ArrayList<>();
        taskQueue = new MinHeap<>();
        nextTaskID = 0;
        loadUsers(configPath); // Load users from file
    }

    /**
     * Loads users and their priorities from the given configuration file.
     * Each line in the file should contain a single integer representing priority.
     *
     * @param configPath path to the configuration file
     * Time complexity: O(n), where n is the number of users
     */
    private void loadUsers(String configPath) {
        try {
            Scanner scanner = new Scanner(new File(configPath));
            int userId = 0;

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                int priority = Integer.parseInt(line);  // Parse priority from line
                users.add(new User(userId, priority));  // Create and add user
                userId++;
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("Configuration file not found: " + e.getMessage());
            System.exit(1);
        } catch (NumberFormatException e) {
            System.err.println("Invalid priority value in config file.");
            System.exit(1);
        }
    }

    /**
     * Adds a new task for the specified user by ID.
     * 
     * @param userID the ID of the user to assign the task to
     * Time complexity: O(log n), where n is the number of tasks in the queue
     */
    public void addTask(int userID) {
        // Validate user ID
        if (userID < 0 || userID >= users.size()) {
            System.err.println("Invalid user ID: " + userID);
            return;
        }

        User user = users.get(userID);
        Task task = new Task(user, nextTaskID++); // Create new task with unique ID
        taskQueue.add(task); // Add task to priority queue
    }

    /**
     * Executes all tasks in order of their priority by polling from the queue.
     * Time complexity: O(n log n), where n is the number of tasks
     */
    public void executeTasks() {
        while (!taskQueue.isEmpty()) {
            System.out.println(taskQueue.poll()); // Remove and print next task
        }
    }

}
