package Main;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Iterator;
import HWSystem.HWSystem;
import HWSystem.Protocols.*;

/**
 * The {@code Main} class to initialize the system, read configuration files,
 * and handle user commands.
 */
public class Main {
   
    /**
     * Main entry point of the program.
     *
     * @param args command-line arguments (configuration file path and log directory)
     */
    public static void main(String[] args) {

        // List to store port configurations
        ArrayList<Port> protocol = new ArrayList<>();
        
        // Max number of devices of each type (Sensors, Displays, Wireless, Motors)
        int maxS = 0, maxD = 0, maxW = 0, maxM = 0;
       

        try {
            // Open the configuration file (config.txt)
            File file = new File(args[0]);
            Scanner scanner = new Scanner(file);

            // Read each line from the configuration file
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                LinkedList<String> parts = new LinkedList<>(Arrays.asList(line.split(":")));


                if (parts.size() == 2) {
                    String key = parts.get(0).trim();  // Key (e.g., "Port Configuration")
                    String value = parts.get(1).trim(); // Value (e.g., "I2C, SPI")

                    switch (key) {
                        case "Port Configuration":
                            // Split comma-separated port configurations
                            LinkedList<String> ports = new LinkedList<>(Arrays.asList(value.split(",")));
                            Iterator<String> iterator = ports.iterator();
                            while(iterator.hasNext()){ 
                                String current = iterator.next();  
                                // Create a new Port object for each protocol
                                Port newPort = new Port();
                                protocol.add(newPort);
                                String fileName = String.format("%s_%d.log", current.trim(), protocol.indexOf(newPort)); //AI
                                // Set protocol type based on configuration
                                switch (current.trim()) {
                                    case "I2C":
                                        newPort.setProtocol(new I2C(args[1],fileName));
                                        break;
                                    case "SPI":
                                        newPort.setProtocol(new SPI(args[1],fileName));
                                        break;
                                    case "UART":
                                        newPort.setProtocol(new UART(args[1],fileName));
                                        break;
                                    case "OneWire":
                                        newPort.setProtocol(new OneWire(args[1],fileName));
                                        break;
                                    default:
                                        System.err.println("Invalid protocol name.");
                                        break;
                                }
                            }
                            break;

                        case "# of sensors":
                            // Set the maximum number of sensors allowed
                            maxS = Integer.parseInt(value);
                            break;

                        case "# of displays":
                            // Set the maximum number of displays allowed
                            maxD = Integer.parseInt(value);
                            break;

                        case "# of wireless adapters":
                            // Set the maximum number of wireless adapters allowed
                            maxW = Integer.parseInt(value);
                            break;

                        case "# of motor drivers":
                            // Set the maximum number of motor drivers allowed
                            maxM = Integer.parseInt(value);
                            break;

                        default:
                            // Handle unknown configuration keys
                            System.err.println("Unknown key: " + key);
                            break;
                    }
                }
            }
            scanner.close(); // Close the scanner after reading the file

            // Initialize HWSystem with protocol and device limits from the config file
            Scanner scannerr = new Scanner(System.in);
            HWSystem system = new HWSystem(protocol, maxS, maxD, maxW, maxM);

            // Queue to store user commands
            Queue<String> inputs = new LinkedList<>();
            boolean flag = true;

            // Read user commands until "exit" is entered
            do {
                String input = scannerr.nextLine();
                inputs.add(input);

                // Split input by spaces into command and arguments
                LinkedList<String> words = new LinkedList<>(Arrays.asList(input.split("\\s+")));

                if (words.get(0).equals("exit")) {
                    flag = false;
                }
            } while (flag);

            // Process user commands
            while (!inputs.isEmpty()) {
                LinkedList<String> words = new LinkedList<>(Arrays.asList(inputs.poll().split("\\s+")));


                try {
                    switch (words.get(0)) {
                        case "turnON":
                            // Turn on device at specified port
                            system.turnON(Integer.parseInt(words.get(1)));
                            break;
                        case "turnOFF":
                            // Turn off device at specified port
                            system.turnOFF(Integer.parseInt(words.get(1)));
                            break;
                        case "addDev":
                            // Add a new device
                            system.addDevice(words.get(1), Integer.parseInt(words.get(2)), Integer.parseInt(words.get(3)));
                            break;
                        case "rmDev":
                            // Remove a device
                            system.removeDevice(Integer.parseInt(words.get(1)));
                            break;
                        case "list":
                            if (words.get(1).equals("ports")) {
                                // List all ports and their status
                                system.listPorts();
                            } else {
                                // List all devices of the specified type
                                system.list(words.get(1));
                            }
                            break;
                        case "readSensor":
                            // Read data from a sensor
                            system.readSensor(Integer.parseInt(words.get(1)));
                            break;
                        case "printDisplay":
                            // Print data on a specified display
                            String str ="";
                            for(int i=2;i<words.size();i++){
                                str+=words.get(i);
                                if(i!=words.size()-1)
                                str+= " ";
                            }
                            system.printDisplay(Integer.parseInt(words.get(1)), str);
                            break;
                        case "readWireless":
                            // Read data from a wireless device
                            system.readWireless(Integer.parseInt(words.get(1)));
                            break;
                        case "writeWireless":
                            String str1 ="";
                            for(int i=2;i<words.size();i++){
                                str1+=words.get(i);
                                if(i!=words.size()-1)
                                str1+= " ";
                            }
                            // Write data to a wireless device
                            system.writeWireless(Integer.parseInt(words.get(1)), str1);
                            break;
                        case "setMotorSpeed":
                            // Set motor speed
                            system.setMotorSpeed(Integer.parseInt(words.get(1)), Integer.parseInt(words.get(2)));
                            break;
                        case "exit":
                            // Exit the program
                            System.out.println("Exiting ...");
                            break;
                        default:
                            // Handle invalid commands
                            System.err.println("Invalid command.");
                            break;
                    }
                } catch (Exception e) {
                    System.err.println("Invalid command: " + e.getMessage());
                }
            }

            scannerr.close(); // Close the scanner when done

            // Write protocol logs to files
            Iterator<Port> iterator = protocol.iterator();
            while (iterator.hasNext()) {
                iterator.next().getProtocol().close();
            }


        } catch (Exception e) {
            // Handle general exceptions during initialization
            System.err.println("Configuration file error: " + e.getMessage());
        }
    }
}
