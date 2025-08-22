package HWSystem.Devices;

/**
 * Represents a motor driver device.
 * <p>
 * This abstract class extends {@code Device} and defines an abstract method for setting 
 * the motor speed. It also provides a method to retrieve the device type.
 * </p>
 */
public abstract class MotorDriver extends Device {

    /**
     * Returns the type of the device as {@code "MotorDriver"}.
     *
     * @return the device type, "MotorDriver"
     */
    @Override
    public String getDevType() {
        return "MotorDriver";
    }

    /**
     * Sets the speed of the motor.
     * <p>
     * Implementation must be provided by subclasses to set the actual motor speed.
     * </p>
     *
     * @param speed the speed value to be set for the motor
     */
    public abstract void setMotorSpeed(int speed);
}
