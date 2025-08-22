package Main;

import java.util.Scanner;
import MyTaskManager.*;

/**
 * Entry point of the Task Manager application.
 * Accepts a config file as an argument and reads user input to add and execute tasks.
 */
public class Main {

    /**
     * Main method that starts the task manager system.
     * 
     * @param args the command-line arguments (expects one argument: config file path)
     */
    public static void main(String[] args) {

        // Check for required config file argument
        if (args.length != 1) {
            System.err.println("Usage: java Main <config_file_path>"); //AI
            return;
        }

        // Initialize TaskManager with user config
        TaskManager manager = new TaskManager(args[0]);
        Scanner scanner = new Scanner(System.in);

        // Read input lines from user
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine().trim();

            // If input is "execute", run all queued tasks and exit
            if (input.equals("execute")) {
                manager.executeTasks();
                break;
            }

            try {
                int userID = Integer.parseInt(input);  // Try to parse user ID
                manager.addTask(userID);              // Add task for given user
            } catch (NumberFormatException e) {
                System.err.println("Invalid User ID: " + input); // Handle invalid input
            }
        }

        scanner.close(); // Close scanner resource
    }
}
