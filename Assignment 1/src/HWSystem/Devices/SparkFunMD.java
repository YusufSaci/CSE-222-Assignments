package HWSystem.Devices;

// SparkFunMD class extending MotorDriver 
public class SparkFunMD extends MotorDriver {
    
    // Method to turn on the SparkFunMD motor driver
    public void turnOn() {
        System.out.println(getName() + ": Turning ON."); // Print message
        getProtocol().write("TURN ON"); // Send command to turn on
        state = State.ON; // Update state to ON
    }

    // Method to turn off the SparkFunMD motor driver
    public void turnOff() {
        System.out.println(getName() + ": Turning OFF."); // Print message
        getProtocol().write("TURN OFF"); // Send command to turn off
        state = State.OFF; // Update state to OFF
    }

    // Method to return the name of the motor driver
    public String getName() {
        return "SparkFunMD";
    }

    // Method to set the motor speed and send it to the protocol
    public void setMotorSpeed(int speed) {
        getProtocol().write(String.format(" setting speed to %d", speed)); // Write motor speed to protocol
    }
}
