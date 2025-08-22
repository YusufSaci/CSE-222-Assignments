package HWSystem.Devices;

/**
 * Represents an Inertial Measurement Unit (IMU) sensor.
 * <p>
 * This abstract class extends {@code Sensor} and defines methods for retrieving
 * acceleration and rotation data. Subclasses must implement the specific behavior
 * for data retrieval.
 * </p>
 */
public abstract class IMUSensor extends Sensor {

    /**
     * Retrieves the acceleration value from the IMU sensor.
     * <p>
     * Implementation must be provided by subclasses to return the actual acceleration data.
     * </p>
     *
     * @return a float value representing the acceleration
     */
    public abstract float getAccel();

    /**
     * Retrieves the rotation value from the IMU sensor.
     * <p>
     * Implementation must be provided by subclasses to return the actual rotation data.
     * </p>
     *
     * @return a float value representing the rotation
     */
    public abstract float getRot();

    /**
     * Returns the type of the sensor as {@code "IMUSensor"}.
     *
     * @return the sensor type, "IMUSensor"
     */
    @Override
    public String getSensType() {
        return "IMUSensor";
    }

    /**
     * Formats the sensor's acceleration and rotation data into a string.
     *
     * @return a formatted string in the format {@code "Accel: <acceleration_value>, Rot: <rotation_value>"}.
     */
    @Override
    public String data2String() {
        return String.format("Accel: %.2f, Rot: %.2f.", getAccel(), getRot());
    }
}
