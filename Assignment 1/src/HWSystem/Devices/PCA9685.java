package HWSystem.Devices;

// PCA9685 class extending MotorDriver 
public class PCA9685 extends MotorDriver {

    // Method to turn on the PCA9685 motor driver
    public void turnOn() {
        System.out.println(getName() + ": Turning ON."); // Print message
        getProtocol().write("TURN ON"); // Send command to turn on
        state = State.ON; // Update state to ON
    }

    // Method to turn off the PCA9685 motor driver
    public void turnOff() {
        System.out.println(getName() + ": Turning OFF."); // Print message
        getProtocol().write("TURN OFF"); // Send command to turn off
        state = State.OFF; // Update state to OFF
    }

    // Method to return the name of the motor driver
    public String getName() {
        return "PCA9685";
    }

    // Method to set the motor speed and send it to the protocol
    public void setMotorSpeed(int speed) {
        getProtocol().write(String.format(" setting speed to %d ", speed)); // Write motor speed to protocol
    }
}
