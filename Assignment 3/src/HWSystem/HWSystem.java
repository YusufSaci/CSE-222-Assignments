package HWSystem;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

import HWSystem.Devices.*;
import HWSystem.Protocols.Port;

/**
 * Represents the hardware system that manages devices and their connections.
 * <p>
 * This class manages the configuration of ports, the addition and removal of devices, 
 * and ensures that devices are properly connected through the appropriate protocol.
 * </p>
 */
public class HWSystem {

    /** List of ports in the system. */
    private ArrayList<Port> ports;

    /** List of stacks used for logging device activity. */

    /** Maximum number of sensors, displays, wireless devices, and motor drivers allowed. */
    private int maxS = 0, maxD = 0, maxW = 0, maxM = 0;

    /** List of sensors connected to the system. */
    private ArrayList<Sensor> sensors = new ArrayList<>();

    /** List of displays connected to the system. */
    private ArrayList<Display> displays = new ArrayList<>();

    /** List of wireless I/O devices connected to the system. */
    private ArrayList<WirelessIO> wirelessIOs = new ArrayList<>();

    /** List of motor drivers connected to the system. */
    private ArrayList<MotorDriver> motorDrivers = new ArrayList<>();

    /**
     * Constructs an {@code HWSystem} object with specified ports and device limits.
     *
     * @param ports the list of ports to be used in the system
     * @param maxS the maximum number of sensors allowed
     * @param maxD the maximum number of displays allowed
     * @param maxW the maximum number of wireless I/O devices allowed
     * @param maxM the maximum number of motor drivers allowed
     */
    public HWSystem(ArrayList<Port> ports, int maxS, int maxD, int maxW, int maxM) {

        this.ports = ports;
        this.maxS = maxS;
        this.maxD = maxD;
        this.maxW = maxW;
        this.maxM = maxM;
      

        // Initialize lists with null values based on the specified sizes
        fillNull(sensors, maxS);
        fillNull(displays, maxD);
        fillNull(motorDrivers, maxM);
        fillNull(wirelessIOs, maxW);
    }

    /**
     * Fills the specified list with {@code null} values up to the specified size.
     *
     * @param list the list to be filled with {@code null} values
     * @param size the number of elements to fill in the list
     * @param <T> the type of elements in the list
     */
    private <T> void fillNull(ArrayList<T> list, int size) {
        ListIterator<T> iterator = list.listIterator();
        for (int i = 0; i < size; i++) {
            iterator.add(null);
        }
    }

    /**
     * Controls the addition of a device to the list.
     * <p>
     * This method checks if the protocol type is compatible with the device type,
     * if the device ID is within the valid range, and if the device ID is already in use.
     * </p>
     *
     * @param list the list to which the device will be added
     * @param portID the ID of the port to which the device will be connected
     * @param devID the ID of the device to be added
     * @param name the valid protocol names for the device
     * @param maxDevice the maximum number of devices allowed
     * @param <T> the type of device
     * @return {@code true} if the device can be added; {@code false} otherwise
     */
    private <T> boolean control(ArrayList<T> list, int portID, int devID, String[] name, int maxDevice) {
        boolean flag = true;

        // Check if the protocol is compatible with the device type
        for (int i = 0; i < name.length; i++) {
            if (ports.get(portID).getProtocol().getProtocolName().equals(name[i])) {
                flag = false;
            }
        }

        if (flag) {
            System.err.println("Incompatible port type.");
            return false;
        }

        // Check if the device ID is within valid range
        if (devID >= list.size() || devID < 0) {
            System.err.println("devID out of bounds.");
            return false;
        }

        // Check if the device ID is already in use
        if (devID < list.size() && list.get(devID) != null) {
            System.err.println("Already used devID.");
            return false;
        }

        return true;
    }



    /**
     * Adds a device to a specific port.
     * <p>
     * This method checks if the specified port is available, verifies the protocol compatibility, 
     * and creates the appropriate device instance based on the device name.
     * </p>
     *
     * @param devName the name of the device to be added (e.g., "SparkFunMD", "Wifi", "OLED")
     * @param portID the ID of the port to which the device will be connected
     * @param devID the ID to be assigned to the device
     */
    public void addDevice(String devName, int portID, int devID) {
        // Check if the port ID is within bounds
        if (portID >= ports.size() || portID < 0) {
            System.err.println("portID out of bounds");
            return;
        }

        // Check if the port is empty (no device connected)
        if (ports.get(portID).isEmpty()) {
            boolean flag = false;

            // Add device based on its name (type)
            switch (devName) {

                case "SparkFunMD":
                    // Check if motor driver can be added to this port (using SPI protocol)
                    if (control(motorDrivers, portID, devID, new String[]{"SPI"}, maxM)) {
                        motorDrivers.set(devID, new SparkFunMD());
                        ports.get(portID).connectDevice(motorDrivers.get(devID));
                        flag = true;
                    }
                    break;

                case "PCA9685":
                    // Check if PCA9685 motor driver can be added (using I2C protocol)
                    if (control(motorDrivers, portID, devID, new String[]{"I2C"}, maxM)) {
                        motorDrivers.set(devID, new PCA9685());
                        ports.get(portID).connectDevice(motorDrivers.get(devID));
                        flag = true;
                    }
                    break;

                case "Wifi":
                    // Check if Wifi device can be added (using SPI or UART protocol)
                    if (control(wirelessIOs, portID, devID, new String[]{"SPI", "UART"}, maxW)) {
                        wirelessIOs.set(devID, new Wifi());
                        ports.get(portID).connectDevice(wirelessIOs.get(devID));
                        flag = true;
                    }
                    break;

                case "Bluetooth":
                    // Check if Bluetooth device can be added (using UART protocol)
                    if (control(wirelessIOs, portID, devID, new String[]{"UART"}, maxW)) {
                        wirelessIOs.set(devID, new Bluetooth());
                        ports.get(portID).connectDevice(wirelessIOs.get(devID));
                        flag = true;
                    }
                    break;

                case "OLED":
                    // Check if OLED display can be added (using SPI protocol)
                    if (control(displays, portID, devID, new String[]{"SPI"}, maxD)) {
                        displays.set(devID, new OLED());
                        ports.get(portID).connectDevice(displays.get(devID));
                        flag = true;
                    }
                    break;

                case "LCD":
                    // Check if LCD display can be added (using I2C protocol)
                    if (control(displays, portID, devID, new String[]{"I2C"}, maxD)) {
                        displays.set(devID, new LCD());
                        ports.get(portID).connectDevice(displays.get(devID));
                        flag = true;
                    }
                    break;

                case "GY951":
                    // Check if GY951 sensor can be added (using SPI or UART protocol)
                    if (control(sensors, portID, devID, new String[]{"SPI", "UART"}, maxS)) {
                        sensors.set(devID, new GY951());
                        ports.get(portID).connectDevice(sensors.get(devID));
                        flag = true;
                    }
                    break;

                case "MPU6050":
                    // Check if MPU6050 sensor can be added (using I2C protocol)
                    if (control(sensors, portID, devID, new String[]{"I2C"}, maxS)) {
                        sensors.set(devID, new MPU6050());
                        ports.get(portID).connectDevice(sensors.get(devID));
                        flag = true;
                    }
                    break;

                case "BME280":
                    // Check if BME280 sensor can be added (using I2C or SPI protocol)
                    if (control(sensors, portID, devID, new String[]{"I2C", "SPI"}, maxS)) {
                        sensors.set(devID, new BME280());
                        ports.get(portID).connectDevice(sensors.get(devID));
                        flag = true;
                    }
                    break;

                case "DHT11":
                    // Check if DHT11 sensor can be added (using OneWire protocol)
                    if (control(sensors, portID, devID, new String[]{"OneWire"}, maxS)) {
                        sensors.set(devID, new DHT11());
                        ports.get(portID).connectDevice(sensors.get(devID));
                        flag = true;
                    }
                    break;

                default:
                    // If the device name is not valid, print an error message
                    System.err.println("Invalid device name.");
                    flag = false;
                    break;
            }

            // Finalize connection if the device was added successfully
            if (flag) {
                System.out.println("Device added.");
                ports.get(portID).getDevice().setPortID(portID);
                ports.get(portID).getDevice().setID(devID);
                ports.get(portID).getDevice().setProtocol(ports.get(portID).getProtocol());
            }
        } else {
            // If the port is already occupied, print an error message
            System.err.println("Port is already occupied.");
        }
    }


    /**
     * Removes a device from a specific port.
     * <p>
     * This method checks if the specified port is occupied, verifies that the device is turned off,
     * and removes the device if all conditions are met.
     * </p>
     *
     * @param portID the ID of the port from which the device will be removed
     */
    public void removeDevice(int portID) {
        if (portID >= ports.size() || portID < 0) {
            System.err.println("portID out of bounds");
            return;
        }
        // Check if the port is empty (no device connected)
        if (ports.get(portID).isEmpty()) {
            System.err.println("Empty port");
        }
        // Check if the device is active (ON)
        else if (ports.get(portID).getDevice().getState() == State.ON) {
            System.err.println("Cannot remove, device is ON.");
        } else {
            // Remove device based on its type (Display, WirelessIO, MotorDriver, etc.)
            switch (ports.get(portID).getDevice().getDevType()) {
                case "Display":
                    // Remove from display list
                    displays.set(ports.get(portID).getDevice().getID(), null);
                    break;
                case "WirelessIO":
                    // Remove from wireless list
                    wirelessIOs.set(ports.get(portID).getDevice().getID(), null);
                    break;
                case "MotorDriver":
                    // Remove from motor driver list
                    motorDrivers.set(ports.get(portID).getDevice().getID(), null);
                    break;
                case "TempSensor Sensor":
                    // Remove from sensor list
                    sensors.set(ports.get(portID).getDevice().getID(), null);
                    break;
                case "IMUSensor Sensor":
                    // Remove from sensor list
                    sensors.set(ports.get(portID).getDevice().getID(), null);
                    break;
                default:
                    System.err.println("Unknown device type.");
                    break;
            }
            // Disconnect the device from the port
            ports.get(portID).disconnectDevice();
            System.out.println("Device removed.");
        }
    }

    /**
     * Lists all ports and their current status.
     * <p>
     * This method iterates through all ports and displays their status,
     * including the connected device information (if available).
     * </p>
     */
    public void listPorts() {
        System.out.println("list of ports:");
        int i = 0;

        // Iterate through all ports
        Iterator<Port> iterator = ports.iterator();
        while (iterator.hasNext()) {
            Port port = iterator.next();

            // Print port ID and protocol
            System.out.print(i + " " + port.getProtocol().getProtocolName() + " ");

            // Check if the port is empty or occupied
            if (port.isEmpty()) {
                System.out.print("empty");
            } else {
                // Print the device details if the port is occupied
                System.out.print("occupied ");
                System.out.print(port.getDevice().getName() + " " +
                                port.getDevice().getDevType() + " " +
                                port.getDevice().getID() + " " +
                                port.getDevice().getState());
            }
            System.out.println();
            i++;
        }
    }

    /**
     * Turns on the device connected to a specific port.
     * <p>
     * This method verifies that the specified port is occupied and the device is off before turning it on.
     * </p>
     *
     * @param portID the ID of the port where the device is connected
     */
    public void turnON(int portID) {
        if (portID >= ports.size() || portID < 0) {
            System.err.println("portID out of bounds");
            return;
        }

        // Check if the port is not empty
        if (!ports.get(portID).isEmpty()) {
            // If the device is already on, print a message
            if (ports.get(portID).getDevice().getState() == State.ON) {
                System.err.println("The device is already on.");
            } else {
                // Turn the device on
                ports.get(portID).getDevice().turnOn();
            }
        } else {
            // Print a message if the port is empty
            System.err.println("Empty port.");
        }
    }

    /**
     * Turns off the device connected to a specific port.
     * <p>
     * This method verifies that the specified port is occupied and the device is on before turning it off.
     * </p>
     *
     * @param portID the ID of the port where the device is connected
     */
    public void turnOFF(int portID) {
        if (portID >= ports.size() || portID < 0) {
            System.err.println("portID out of bounds");
            return;
        }

        // Check if the port is not empty
        if (!ports.get(portID).isEmpty()) {
            // If the device is already off, print a message
            if (ports.get(portID).getDevice().getState() == State.OFF) {
                System.err.println("The device is already off.");
            } else {
                // Turn the device off
                ports.get(portID).getDevice().turnOff();
            }
        } else {
            // Print a message if the port is empty
            System.err.println("Empty port.");
        }
    }


    /**
     * Lists all devices of a specific type.
     * <p>
     * This method lists all connected devices of a given type (e.g., Sensor, Display, etc.)
     * along with their ID, port number, and protocol type.
     * </p>
     *
     * @param deviceType the type of the device to list (e.g., "Sensor", "Display", "MotorDriver")
     */
    public void list(String deviceType) {
        if(deviceType.equals("Sensor") || deviceType.equals("Display") || deviceType.equals("WirelessIO")|| deviceType.equals("MotorDriver")){

            System.out.println("list of " + deviceType + ":");
            int i = 0;
            boolean flag = true;

            Iterator<Port> iterator = ports.iterator();

            // Iterate through all ports
            while (iterator.hasNext()) {
                Port port = iterator.next();

                // Check if the port is occupied and matches the requested device type
                if (!port.isEmpty() && port.getDevice().getDevType().equals(deviceType)) {
                    System.out.println(port.getDevice().getName() + " " +
                                    port.getDevice().getID() + " " +
                                    i + " " +
                                    port.getProtocol().getProtocolName());
                    flag = false;
                }
                // Special case for listing sensors (TempSensor, IMUSensor)
                else if (!port.isEmpty() && deviceType.equals("Sensor") &&
                        (port.getDevice().getDevType().equals("TempSensor Sensor") || 
                        port.getDevice().getDevType().equals("IMUSensor Sensor"))) {
                    System.out.println(port.getDevice().getName() + " " +
                                    port.getDevice().getID() + " " +
                                    i + " " +
                                    port.getProtocol().getProtocolName());
                    flag = false;
                }
                i++;
            }

            // If no devices of the specified type are found
            if (flag) {
                System.err.println("There are no devices of this type connected.");
            }
        }else{
            System.err.println("Wrong device type.");

        }   
    }

    /**
     * Reads data from a specific sensor device.
     * <p>
     * This method reads data from a sensor connected to the system if it is active.
     * </p>
     *
     * @param devID the ID of the sensor device
     */
    public void readSensor(int devID) {
        if (sensors.size() > devID && devID >= 0 && sensors.get(devID) != null) {
            if (sensors.get(devID).getState() == State.ON) {
                System.out.println(sensors.get(devID).getName() + " " +
                                sensors.get(devID).getDevType() + ": " +
                                sensors.get(devID).data2String());
            } else {
                System.err.println("Cannot read, device is OFF.");
            }
        } else if (sensors.size() <= devID || devID < 0 ) {
            System.err.println("devID out of bounds.");
        } else {
            System.err.println("There is no sensor with devID " + devID);
        }
    }

    /**
     * Prints data on a specific display device.
     * <p>
     * This method sends data to a display and prints it if the display is active.
     * </p>
     *
     * @param devID the ID of the display device
     * @param data the data to be printed on the display
     */
    public void printDisplay(int devID, String data) {
        if (displays.size() > devID && devID >= 0 && displays.get(devID) != null) {
            if (displays.get(devID).getState() == State.ON) {
                displays.get(devID).printData(data);
            } else {
                System.err.println("Cannot print, device is OFF.");
            }
        } else if (displays.size() <= devID || devID < 0 ) {
            System.err.println("devID out of bounds.");
        } else {
            System.err.println("There is no display with devID " + devID);
        }
    }

    /**
     * Reads data from a specific wireless I/O device.
     * <p>
     * This method reads data from a wireless device connected to the system if it is active.
     * </p>
     *
     * @param devID the ID of the wireless device
     */
    public void readWireless(int devID) {
        if (wirelessIOs.size() > devID  && devID >= 0 && wirelessIOs.get(devID) != null) {
            if (wirelessIOs.get(devID).getState() == State.ON) {
                System.out.print(wirelessIOs.get(devID).recvData());
            } else {
                System.err.println("Cannot read, device is OFF.");
            }
        } else if (wirelessIOs.size() <= devID || devID < 0) {
            System.err.println("devID out of bounds.");
        } else {
            System.err.println("There is no wireless device with devID " + devID);
        }
    }

    /**
     * Sends data to a specific wireless I/O device.
     * <p>
     * This method sends data to a wireless device connected to the system if it is active.
     * </p>
     *
     * @param devID the ID of the wireless device
     * @param data the data to be sent to the device
     */
    public void writeWireless(int devID, String data) {
        if (wirelessIOs.size() > devID && devID >= 0 && wirelessIOs.get(devID) != null) {
            if (wirelessIOs.get(devID).getState() == State.ON) {
                wirelessIOs.get(devID).sendData(data);
            } else {
                System.err.println("Cannot write, device is OFF.");
            }
        } else if (wirelessIOs.size() <= devID || devID < 0) {
            System.err.println("devID out of bounds.");
        } else {
            System.err.println("There is no wireless device with devID " + devID);
        }
    }

    /**
     * Sets the speed of a specific motor driver.
     * <p>
     * This method sets the speed of a motor driver connected to the system if it is active.
     * </p>
     *
     * @param devID the ID of the motor driver
     * @param speed the speed value to be set
     */
    public void setMotorSpeed(int devID, int speed) {
        if (motorDrivers.size() > devID && devID >= 0 && motorDrivers.get(devID) != null) {
            if (motorDrivers.get(devID).getState() == State.ON) {
                motorDrivers.get(devID).setMotorSpeed(speed);
            } else {
                System.err.println("Cannot set motor speed, device is OFF.");
            }
        } else if (motorDrivers.size() <= devID|| devID < 0) {
            System.err.println("devID out of bounds.");
        } else {
            System.err.println("There is no motor driver with devID " + devID);
        }
    }

}

