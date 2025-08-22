import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    
    public static void main(String [] args){
        
        Scanner scanner = new Scanner(System.in); 
        PlanetSystem system = new PlanetSystem(); // Main system that holds the star and all celestial bodies
        boolean flag = false; // Flag to indicate if the system has been successfully initialized
        boolean exit = true;

        while (exit && scanner.hasNextLine()) {
            String input = scanner.nextLine(); // Read user command from console
            String[] tokens = input.split("\\s+"); // Split the command and parameters by space
            String command = tokens[0]; // First word is always the command

            try {
                switch (command) {

                    // ------------------- CREATE PLANET SYSTEM -------------------
                    case "create":
                        if (tokens[1].equals("planetSystem")) {
                            // Parse parameters: name, temperature, pressure, humidity, radiation
                            String name = tokens[2];
                            double temp = Double.parseDouble(tokens[3]);
                            double pressure = Double.parseDouble(tokens[4]);
                            double humidity = Double.parseDouble(tokens[5]);
                            double radiation = Double.parseDouble(tokens[6]);

                            // Try to create the planetary system (star must have 0 humidity)
                            if(flag == true){
                                System.err.println("There is already a planet system.");
                            }
                            else if (!system.createPlanetSystem(name, temp, pressure, humidity, radiation) && flag==false) {
                                flag = false; // Creation failed
                            } 
                            else {
                                System.out.println("Planet system created.");
                                flag = true; // Creation succeeded
                            }

                            // Visual separator for clarity
                            System.out.println("-------------------------------------------------");
                        }
                        break;

                    // ------------------- ADD PLANET -------------------
                    case "addPlanet":
                        if (flag) { // Only allow if system is created
                            system.addPlanet(tokens[1], tokens[2],
                                Double.parseDouble(tokens[3]), Double.parseDouble(tokens[4]),
                                Double.parseDouble(tokens[5]), Double.parseDouble(tokens[6]));
                        } else {
                            System.err.println("there is no planet system."); // Prevent adding without a system
                        }
                        System.out.println("-------------------------------------------------");
                        break;

                    // ------------------- ADD SATELLITE -------------------
                    case "addSatellite":
                        if (flag) { // Only allow if system is created
                            system.addSatellite(tokens[1], tokens[2],
                                Double.parseDouble(tokens[3]), Double.parseDouble(tokens[4]),
                                Double.parseDouble(tokens[5]), Double.parseDouble(tokens[6]));
                        } else {
                            System.err.println("there is no planet system."); // Prevent adding without a system
                        }
                        System.out.println("-------------------------------------------------");
                        break;

                    // ------------------- FIND RADIATION ANOMALIES -------------------
                    case "findRadiationAnomalies":
                        if (flag) {
                            double threshold = Double.parseDouble(tokens[1]); // Radiation threshold
                            if (threshold < 0) {
                                System.err.println("threshold must be greater than 0.");
                            } else {
                                // Get list of nodes with radiation greater than threshold
                                List<Node> anomalies = system.findRadiationAnomalies(threshold);
                                for (Node anomaly : anomalies) {
                                    System.out.println(anomaly); // Print each anomaly's data
                                }
                            }
                        } else {
                            System.err.println("there is no planet system.");
                        }
                        System.out.println("-------------------------------------------------");
                        break;

                    // ------------------- GET PATH TO SPECIFIC NODE -------------------
                    case "getPathTo":
                        if (flag) {
                            Stack<String> path = system.getPathTo(tokens[1]); // Get path as stack
                            if (path == null) {
                                System.err.println(tokens[1] + " is not found."); // Node not found
                            } else {
                                // Print the path in reverse (root to target)
                                while (!path.empty()) {
                                    System.out.print(path.pop());
                                    if (path.size() != 0) {
                                        System.out.print(" -> ");
                                    }
                                }
                                System.out.println(); // End of path output
                            }
                        } else {
                            System.err.println("there is no planet system.");
                        }
                        System.out.println("-------------------------------------------------");
                        break;

                    // ------------------- PRINT MISSION REPORT -------------------
                    case "printMissionReport":
                        if (flag) {
                            if (tokens.length == 1) {
                                system.printMissionReport(); // Print full system report
                            } else {
                                system.printMissionReport(tokens[1]); // Print specific node report
                            }
                        } else {
                            System.err.println("there is no planet system.");
                        }
                        System.out.println("-------------------------------------------------");
                        break;

                    // ------------------- EXIT PROGRAM -------------------
                    case "exit":
                        System.out.println("Exiting...");
                        exit = false;
                        break;

                    // ------------------- UNKNOWN COMMAND -------------------
                    default:
                        System.err.println("Unknown command!"); // Catch unrecognized commands
                }
            } catch (Exception e) {
                // Generic error handling for exceptions like index out of bounds or parse errors
                System.err.println("Error: " + e.getMessage());
            }
        }
        scanner.close(); // Close the input scanner

    }
}
