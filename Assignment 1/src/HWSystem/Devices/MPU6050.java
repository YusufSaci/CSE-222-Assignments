package HWSystem.Devices;

// MPU6050 class extending IMUSensor 
public class MPU6050 extends IMUSensor {
   
    // Method to turn on the MPU6050 sensor
    public void turnOn() {
        System.out.println(getName() + ": Turning ON."); // Print message
        getProtocol().write("TURN ON"); // Send command to turn on
        state = State.ON; // Update state to ON
    }

    // Method to turn off the MPU6050 sensor
    public void turnOff() {
        System.out.println(getName() + ": Turning OFF."); // Print message
        getProtocol().write("TURN OFF"); // Send command to turn off
        state = State.OFF; // Update state to OFF
    }

    // Method to return the name of the sensor
    public String getName() {
        return "MPU6050";
    }
}
