package HWSystem.Devices;

/**
 * Represents a PCA9685 motor driver.
 * <p>
 * This class extends {@code MotorDriver} and provides methods to turn the motor driver on or off,
 * set the motor speed, and retrieve the name of the motor driver.
 * </p>
 */
public class PCA9685 extends MotorDriver {

    /**
     * Turns on the PCA9685 motor driver and updates its state to {@code ON}.
     */
    @Override
    public void turnOn() {
        getProtocol().write("turnON");

        System.out.println(getName() + ": Turning ON.");
        state = State.ON; // Update state to ON
    }

    /**
     * Turns off the PCA9685 motor driver and updates its state to {@code OFF}.
     */
    @Override
    public void turnOff() {
        getProtocol().write("turnOFF");

        System.out.println(getName() + ": Turning OFF.");
        state = State.OFF; // Update state to OFF
    }

    /**
     * Returns the name of the motor driver.
     *
     * @return the name of the motor driver, "PCA9685"
     */
    @Override
    public String getName() {
        return "PCA9685";
    }

    /**
     * Sets the speed of the motor and sends the speed value to the communication protocol.
     *
     * @param speed the speed value to be set for the motor
     */
    @Override
    public void setMotorSpeed(int speed) {
        getProtocol().write(Integer.toString(speed));
        System.out.printf("%s: Setting speed to %d.\n", getName(), speed);
    }
}
