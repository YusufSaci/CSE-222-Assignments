package HWSystem.Devices;

/**
 * Represents an MPU6050 IMU sensor (Inertial Measurement Unit).
 * <p>
 * This class extends {@code IMUSensor} and provides methods to turn the sensor on and off,
 * retrieve acceleration and rotation values, and get the name of the sensor.
 * </p>
 */
public class MPU6050 extends IMUSensor {

    /**
     * Turns on the MPU6050 sensor and updates its state to {@code ON}.
     */
    @Override
    public void turnOn() {
        getProtocol().write("turnON");

        System.out.println(getName() + ": Turning ON.");
        state = State.ON; // Update state to ON
    }

    /**
     * Turns off the MPU6050 sensor and updates its state to {@code OFF}.
     */
    @Override
    public void turnOff() {
        getProtocol().write("turnOFF");

        System.out.println(getName() + ": Turning OFF.");
        state = State.OFF; // Update state to OFF
    }

    /**
     * Returns the acceleration value from the MPU6050 sensor.
     * <p>
     * For this example, it returns a constant value of {@code 1f}.
     * </p>
     *
     * @return the current acceleration value
     */
    @Override
    public float getAccel() {
        getProtocol().read();
        return 1f;
    }

    /**
     * Returns the rotation value from the MPU6050 sensor.
     * <p>
     * For this example, it returns a constant value of {@code 0.50f}.
     * </p>
     *
     * @return the current rotation value
     */
    @Override
    public float getRot() {
        getProtocol().read();
        return 0.50f;
    }

    /**
     * Returns the name of the sensor.
     *
     * @return the sensor name, "MPU6050"
     */
    @Override
    public String getName() {
        return "MPU6050";
    }
}
