package HWSystem.Devices;

// Abstract class representing a Motor Driver device
public abstract class MotorDriver extends Device {
   

    // Method to return the type of device as "MotorDriver"
    public String getDevType() {
        return "MotorDriver";
    }

    // Abstract method to set the motor speed (to be implemented by subclasses)
    public abstract void setMotorSpeed(int speed);
}
