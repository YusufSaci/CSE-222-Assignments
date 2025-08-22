package HWSystem;



import java.util.ArrayList;
import HWSystem.Devices.*;
import HWSystem.Protocols.Port;


public class HWSystem{

    private ArrayList<Port> ports; // List of ports in the system

    private ArrayList<Sensor> sensors = new ArrayList<>(); // List of sensors
    private ArrayList<Display> displays = new ArrayList<>(); // List of displays
    private ArrayList<WirelessIO> wirelessIOs = new ArrayList<>(); // List of wireless I/O devices
    private ArrayList<MotorDriver> motorDrivers = new ArrayList<>(); // List of motor drivers
    int maxS = 0, maxD = 0, maxW = 0, maxM = 0; // Maximum number of devices per type

    // Constructor for HWSystem, accepting ports and max device limits
    public HWSystem(ArrayList<Port> ports, int maxS, int maxD, int maxW, int maxM){
        this.ports = ports; // Initialize the ports list
        this.maxS = maxS; // Initialize max number of sensors
        this.maxD = maxD; // Initialize max number of displays
        this.maxW = maxW; // Initialize max number of wireless I/O devices
        this.maxM = maxM; // Initialize max number of motor drivers
    }

    // Generic method to control the addition of devices to the list
    public <T> boolean control(ArrayList<T> list, int portID , int devID, String[] name , int maxDevice){
        boolean flag = true;
        // Check if the protocol is compatible with the device type
        for(String str : name){
            if(ports.get(portID).getProtocol().getProtocolName().equals(str)){
                flag = false;
            }
        }
        if(flag){
            System.out.println("The port is not compatible with the device");
            return false;
        }
        // Check if the maximum number of devices of this type is reached
        if(list.size() >= maxDevice && devID>=maxDevice){
            System.out.println("You cannot add more devices of this type.");
            return false;
        }
        // Check if the device ID exists in the list
        if(devID > list.size()){
            System.out.println("non-existing devID.");
            return false;
        }
        // Check if the device is already in use
        if(devID < list.size() && list.get(devID) != null){
            System.out.println("The device is already in use.");
            return false;
        }
        return true;
    }

    // Method to add a device to a specific port
    public void addDevice(String devName, int portID, int devID){
        // Check if the port is empty (no device connected)
        if(ports.get(portID).isEmpty()){
            boolean flag = false;
            
            // Add device based on its name (type)
            switch(devName){
                case "SparkFunMD" :
                    // Check if motor driver can be added to this port (using SPI protocol)
                    if(control(motorDrivers, portID, devID, new String[]{"SPI"}, maxM)){
                        // If device ID matches the size of the list, add it
                        if(devID == motorDrivers.size()){
                            motorDrivers.add(new SparkFunMD());
                        }
                        // If the device ID exists but is empty, add it at the specific index
                        else if(devID < motorDrivers.size() && motorDrivers.get(devID) == null){
                            motorDrivers.set(devID, new SparkFunMD());
                        }
                        flag = true;
                        // Connect the motor driver to the port
                        ports.get(portID).connectDevice(motorDrivers.get(devID));
                    } break;

                case "PCA9685" :
                    // Check if PCA9685 motor driver can be added (using I2C protocol)
                    if(control(motorDrivers, portID, devID, new String[]{"I2C"}, maxM)){
                        if(devID == motorDrivers.size()){
                            motorDrivers.add(new PCA9685());
                        }
                        else if(devID < motorDrivers.size() && motorDrivers.get(devID) == null){
                            motorDrivers.set(devID, new PCA9685());
                        }
                        flag = true;
                        ports.get(portID).connectDevice(motorDrivers.get(devID));
                    } break;

                case "Wifi":
                    // Check if Wifi device can be added (using SPI or UART protocol)
                    if(control(wirelessIOs, portID, devID, new String[]{"SPI","UART"}, maxW)){
                        if(devID == wirelessIOs.size()){
                            wirelessIOs.add(new Wifi());
                        }
                        else if(devID < wirelessIOs.size() && wirelessIOs.get(devID) == null){
                            wirelessIOs.set(devID, new Wifi());
                        }
                        flag = true;
                        ports.get(portID).connectDevice(wirelessIOs.get(devID));
                    } break;

                case "Bluetooth":
                    // Check if Bluetooth device can be added (using UART protocol)
                    if(control(wirelessIOs, portID, devID, new String[]{"UART"}, maxW)){
                        if(devID == wirelessIOs.size()){
                            wirelessIOs.add(new Bluetooth());
                        }
                        else if(devID < wirelessIOs.size() && wirelessIOs.get(devID) == null){
                            wirelessIOs.set(devID, new Bluetooth());
                        }
                        flag = true;
                        ports.get(portID).connectDevice(wirelessIOs.get(devID));
                    } break;

                case "OLED" :
                    // Check if OLED display can be added (using SPI protocol)
                    if(control(displays, portID, devID, new String[]{"SPI"}, maxD)){
                        if(devID == displays.size()){
                            displays.add(new OLED());
                        }
                        else if(devID < displays.size() && displays.get(devID) == null){
                            displays.set(devID, new OLED());
                        }
                        flag = true;
                        ports.get(portID).connectDevice(displays.get(devID));
                    } break;

                case "LCD" :
                    // Check if LCD display can be added (using I2C protocol)
                    if(control(displays, portID, devID, new String[]{"I2C"}, maxD)){
                        if(devID == displays.size()){
                            displays.add(new LCD());
                        }
                        else if(devID < displays.size() && displays.get(devID) == null){
                            displays.set(devID, new LCD());
                        }
                        flag = true;
                        ports.get(portID).connectDevice(displays.get(devID));
                    } break;

                case "GY951":
                    // Check if GY951 sensor can be added (using SPI or UART protocol)
                    if(control(sensors, portID, devID, new String[]{"SPI", "UART"}, maxS)){
                        if(devID == sensors.size()){
                            sensors.add(new GY951());
                        }
                        else if(devID < sensors.size() && sensors.get(devID) == null){
                            sensors.set(devID, new GY951());
                        }
                        flag = true;
                        ports.get(portID).connectDevice(sensors.get(devID));
                    } break;

                case "MPU6050" :
                    // Check if MPU6050 sensor can be added (using I2C protocol)
                    if(control(sensors, portID, devID, new String[]{"I2C"}, maxS)){
                        if(devID == sensors.size()){
                            sensors.add(new MPU6050());
                        }
                        else if(devID < sensors.size() && sensors.get(devID) == null){
                            sensors.set(devID, new MPU6050());
                        }
                        flag = true;
                        ports.get(portID).connectDevice(sensors.get(devID));
                    } break;

                case "BME280" :
                    // Check if BME280 sensor can be added (using I2C or SPI protocol)
                    if(control(sensors, portID, devID, new String[]{"I2C", "SPI"}, maxS)){
                        if(devID == sensors.size()){
                            sensors.add(new BME280());
                        }
                        else if(devID < sensors.size() && sensors.get(devID) == null){
                            sensors.set(devID, new BME280());
                        }
                        flag = true;
                        ports.get(portID).connectDevice(sensors.get(devID));
                    } break;

                case "DHT11" :
                    // Check if DHT11 sensor can be added (using OneWire protocol)
                    if(control(sensors, portID, devID, new String[]{"OneWire"}, maxS)){
                        if(devID == sensors.size()){
                            sensors.add(new DHT11());
                        }
                        else if(devID < sensors.size() && sensors.get(devID) == null){
                            sensors.set(devID, new DHT11());
                        }
                        flag = true;
                        ports.get(portID).connectDevice(sensors.get(devID));
                    } break;
                    
                default:
                    // If the device name is not valid, print an error message
                    System.out.println("Wrong input");
                    flag = false;
                    break;
            }
            
            // Finalizing the connection to the port if device was added successfully
            if(flag){
                ports.get(portID).getDevice().setID(devID); // Set device ID
                ports.get(portID).getDevice().setProtocol(ports.get(portID).getProtocol()); // Set protocol
            }
        }
        else{
            // If the port is already occupied, print a message
            System.out.println("The port is already occupied");
        }
    }


    // Method to remove a device from a specific port
    public void removeDevice(int portID){
        // Check if the port is empty (no device connected)
        if(ports.get(portID).isEmpty())
            System.out.println("There is no device in this port yet.");
        // Check if the device is active (ON)
        else if(ports.get(portID).getDevice().getState()==State.ON)
            System.out.println("Device is active.");
        else{
            // Remove device based on its type (Display, WirelessIO, MotorDriver, etc.)
            switch (ports.get(portID).getDevice().getDevType()) {
                case "Display":
                    // Set the corresponding device in the displays list to null
                    displays.set(ports.get(portID).getDevice().getID(), null);
                    break;
                case "WirelessIO":
                    // Set the corresponding device in the wirelessIOs list to null
                    wirelessIOs.set(ports.get(portID).getDevice().getID(), null);
                    break;
                case "MotorDriver":
                    // Set the corresponding device in the motorDrivers list to null
                    motorDrivers.set(ports.get(portID).getDevice().getID(), null);
                    break;
                case "TempSensor Sensor":
                    // Set the corresponding device in the sensors list to null
                    sensors.set(ports.get(portID).getDevice().getID(), null);
                    break;
                case "IMUSensor Sensor":
                    // Set the corresponding device in the sensors list to null
                    sensors.set(ports.get(portID).getDevice().getID(), null);
                    break;
            }
            // Disconnect the device from the port
            ports.get(portID).disconnectDevice();
        } 
    }

    // Method to list all the ports and their current status (empty or occupied)
    public void listPorts(){
        System.out.println("List of ports:");
        int i = 0;
        // Iterate through all ports to display their status
        for(Port port : ports){
            // Print port number and protocol
            System.out.print(i + " " + port.getProtocol().getProtocolName() + " ");
            
            // Check if the port is empty or occupied
            if(port.isEmpty()){
                System.out.print("empty");
            }else{
                // Print the device information if the port is occupied
                System.out.print("occupied ");
                System.out.print(port.getDevice().getName() + " " +  port.getDevice().getDevType() + " " +
                port.getDevice().getID() + " " + port.getDevice().getState());
            }
            System.out.println();  
            i++;
        }
    }

    // Method to turn on the device connected to a specific port
    public void turnON(int portID){
        // Check if the port is not empty
        if(!ports.get(portID).isEmpty()){
            // If the device is already on, print a message
            if( ports.get(portID).getDevice().getState()==State.ON){
                System.out.println("The device is already on.");
            }
            else
                // Otherwise, turn the device on
                ports.get(portID).getDevice().turnOn();
        }
        else{
            // Print a message if the port is empty
            System.out.println("There is no device on this port.");
        }
    }

    // Method to turn off the device connected to a specific port
    public void turnOFF(int portID){
        // Check if the port is not empty
        if(!ports.get(portID).isEmpty()){
            // If the device is already off, print a message
            if( ports.get(portID).getDevice().getState()==State.OFF){
                System.out.println("The device is already off.");
            }
            else
                // Otherwise, turn the device off
                ports.get(portID).getDevice().turnOff();
        }
        else{
            // Print a message if the port is empty
            System.out.println("There is no device on this port.");
        }
    }


    // Method to list devices of a specific type (e.g., Sensors, Displays, etc.)
    public void list(String deviceType){
        System.out.println("List of " + deviceType + ":");
        int i = 0;  // Initialize counter for port numbers
        boolean flag = true;  // Flag to check if any devices of the given type are found

        // Iterate through all ports
        for(Port port : ports){
            // Check if the port has a device and if it matches the given device type
            if(!port.isEmpty() && port.getDevice().getDevType().equals(deviceType)){
                // Print device information (name, ID, port number, protocol)
                System.out.println(port.getDevice().getName() + " " + port.getDevice().getID() + " " + i + " " + port.getProtocol().getProtocolName());
                flag = false;  // Set flag to false since a device of the given type is found
            }
            // Special case for listing sensors (TempSensor, IMUSensor)
            else if(!port.isEmpty() && deviceType.equals("Sensor") && 
                    (port.getDevice().getDevType().equals("TempSensor Sensor") || port.getDevice().getDevType().equals("IMUSensor Sensor"))){
                // Print sensor device information
                System.out.println(port.getDevice().getName() + " " + port.getDevice().getID() + " " + i + " " + port.getProtocol().getProtocolName());
                flag = false;  // Set flag to false since a sensor device is found
            }
            i++;  // Increment port counter
        }
        // If no device of the given type was found, print a message
        if(flag){
            System.out.println("There are no devices of this type connected");
        }
    }

    // Method to read data from a specific sensor device using its devID
    public void readSensor(int devID){            
        // Check if the devID is valid and the sensor is not null
        if(sensors.size() > devID && sensors.get(devID) != null){
            // Check if the sensor is ON
            if(sensors.get(devID).getState() == State.ON){
                // Print sensor data in string format
                System.out.println(sensors.get(devID).getName() + " " + sensors.get(devID).getDevType() + " " + sensors.get(devID).data2String());
            }
            else
                System.out.println("Device is not active.");  // If the sensor is not ON
        }
        else{
            System.out.println("non-existing devID.");  // If the devID is invalid
        }
    }

    // Method to print data on a specific display device using its devID and the data to be printed
    public void printDisplay(int devID, String data){
        // Check if the devID is valid and the display is not null
        if(displays.size() > devID && displays.get(devID) != null){ 
            // Check if the display is ON
            if(displays.get(devID).getState() == State.ON){
                System.out.println(displays.get(devID).getName() + " " + displays.get(devID).getDevType() + " ");
                // Print data on the display
                displays.get(devID).printData(data);
            }
            else
                System.out.println("Device is not active.");  // If the display is not ON
        }
        else{
            System.out.println("non-existing devID.");  // If the devID is invalid
        }
    }

    // Method to read data from a specific wireless I/O device using its devID
    public void readWireless(int devID){
        // Check if the devID is valid and the wireless device is not null
        if(wirelessIOs.size() > devID && wirelessIOs.get(devID) != null){ 
            // Check if the wireless device is ON
            if(wirelessIOs.get(devID).getState() == State.ON){
                System.out.println(wirelessIOs.get(devID).getName() + " " + wirelessIOs.get(devID).getDevType() + " " + wirelessIOs.get(devID).recvData());
            }
            else
                System.out.println("Device is not active.");  // If the wireless device is not ON
        }
        else{
            System.out.println("non-existing devID.");  // If the devID is invalid
        }
    }

    // Method to send data to a specific wireless I/O device using its devID
    public void writeWireless(int devID, String data){
        // Check if the devID is valid and the wireless device is not null
        if(wirelessIOs.size() > devID && wirelessIOs.get(devID) != null){
            // Check if the wireless device is ON
            if(wirelessIOs.get(devID).getState() == State.ON){  
                System.out.print(wirelessIOs.get(devID).getName() + " " + wirelessIOs.get(devID).getDevType() + " ");
                // Send the data to the wireless device
                wirelessIOs.get(devID).sendData(data);
            }
            else
                System.out.println("Device is not active.");  // If the wireless device is not ON
        }
        else{
            System.out.println("non-existing devID.");  // If the devID is invalid
        }
    }

    // Method to set the speed of a specific motor driver using its devID and the desired speed
    public void setMotorSpeed(int devID, int speed){
        // Check if the devID is valid and the motor driver is not null
        if(motorDrivers.size() > devID && motorDrivers.get(devID) != null){ 
            // Check if the motor driver is ON
            if(motorDrivers.get(devID).getState() == State.ON){
                System.out.print(motorDrivers.get(devID).getName() + " " + motorDrivers.get(devID).getDevType() + " ");
                // Set the motor speed
                motorDrivers.get(devID).setMotorSpeed(speed);
            }
            else
                System.out.println("Device is not active.");  // If the motor driver is not ON
        }
        else{
            System.out.println("non-existing devID.");  // If the devID is invalid
        }
    }

}

