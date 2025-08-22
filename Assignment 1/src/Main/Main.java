package Main;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import HWSystem.HWSystem;
import HWSystem.Protocols.*;

public class Main {
    public static void main(String[] args){

        ArrayList<Port> protocol = new ArrayList<>(); // List to store port configurations
        int maxS = 0, maxD = 0, maxW = 0, maxM = 0; // Variables to store the max number of devices of each type (Sensors, Displays, etc.)

        try {
            // Open the configuration file (config.txt)
            File file = new File("config.txt"); 
            Scanner scanner = new Scanner(file);

            // Read each line in the file
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(":"); // Split each line into key-value pairs
                if (parts.length == 2) {
                    String key = parts[0].trim(); // Key (e.g., Port Configuration)
                    String value = parts[1].trim(); // Value (e.g., I2C, SPI, etc.)

                    switch (key) {
                        case "Port Configuration":
                            // Parse the protocols for the ports
                            String[] ports = value.split(",");
                            for (int i = 0; i < ports.length; i++) {
                                protocol.add(new Port());  // Add a new Port to the protocol list
                                switch(ports[i].trim()){
                                    case "I2C":
                                        protocol.get(i).setProtocol(new I2C()); break;
                                    case "SPI":
                                        protocol.get(i).setProtocol(new SPI()); break;
                                    case "UART":
                                        protocol.get(i).setProtocol(new UART()); break;
                                    case "OneWire":
                                        protocol.get(i).setProtocol(new OneWire()); break;
                                    default:
                                        System.out.println("Wrong protocol name."); break; // Handle unknown protocol names
                                }
                            }
                            break;
                        case "# of sensors":
                            maxS = Integer.parseInt(value);  // Set the max number of sensors
                            break;
                        case "# of displays":
                            maxD = Integer.parseInt(value);  // Set the max number of displays
                            break;
                        case "# of wireless adapters":
                            maxW = Integer.parseInt(value);  // Set the max number of wireless adapters
                            break;
                        case "# of motor drivers":
                            maxM = Integer.parseInt(value);  // Set the max number of motor drivers
                            break;
                        default:
                            System.out.println("Unknown key: " + key);  // Handle unknown keys
                            break;
                    }
                }
            }
            scanner.close(); // Close the scanner after reading the file
        } catch (Exception e) {
            e.printStackTrace();  // Print stack trace in case of an exception
        }
        
        // Initialize system with protocol and device limits from the config file
        Scanner scanner = new Scanner(System.in);
        HWSystem system = new HWSystem(protocol, maxS, maxD, maxW, maxM);

        boolean loop = true;  // Loop flag to control the input command loop

        // Main loop for taking commands from the user
        do {
            System.out.print("Command: ");  // Prompt user for a command
        
            String input = scanner.nextLine();  // Read user input
            String[] words = input.split("\\s+");  // Split input by spaces into command and arguments

            try {
                // Process various commands based on the input
                if(words[0].equals("turnON")){
                    system.turnON(Integer.parseInt(words[1]));  // Turn on the device at the specified port
                }
                else if(words[0].equals("turnOFF")){
                    system.turnOFF(Integer.parseInt(words[1]));  // Turn off the device at the specified port
                }
                else if(words[0].equals("addDev")){
                    system.addDevice(words[1], Integer.parseInt(words[2]), Integer.parseInt(words[3]));  // Add a new device
                }
                else if(words[0].equals("rmDev")){
                    system.removeDevice(Integer.parseInt(words[1]));  // Remove a device
                }
                else if(words[0].equals("list") && words[1].equals("ports")){
                    system.listPorts();  // List all ports and their status
                }
                else if(words[0].equals("list")){
                    system.list(words[1]);  // List all devices of the specified type (e.g., sensors, displays)
                }
                else if(words[0].equals("readSensor")){
                    system.readSensor(Integer.parseInt(words[1]));  // Read data from a sensor
                }
                else if(words[0].equals("printDisplay")){
                    system.printDisplay(Integer.parseInt(words[1]), words[2]);  // Print data on the specified display
                }
                else if(words[0].equals("readWireless")){
                    system.readWireless(Integer.parseInt(words[1]));  // Read data from a wireless device
                }
                else if(words[0].equals("writeWireless")){
                    system.writeWireless(Integer.parseInt(words[1]), words[2]);  // Write data to a wireless device
                }
                else if(words[0].equals("setMotorSpeed")){
                    system.setMotorSpeed(Integer.parseInt(words[1]), Integer.parseInt(words[2]));  // Set the speed of a motor
                }
                else if(words[0].equals("exit")){
                    loop = false;  // Exit the loop and end the program
                }
                else{
                    System.out.println("Wrong input.");  // Handle invalid commands
                } 
            } catch (NumberFormatException e) {
                System.out.println("Invalid number format. Please enter valid integers.");  // Handle invalid number format
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Please provide all required parameters.");  // Handle missing parameters
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());  // Handle other exceptions
            }
            System.out.println();  // Print a newline for readability
        } while (loop);  // Continue the loop until the user exits
        scanner.close();  // Close the scanner when done
    }
}
