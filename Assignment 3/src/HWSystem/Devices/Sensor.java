package HWSystem.Devices;

/**
 * Represents a sensor device, extending {@code Device}.
 * <p>
 * This abstract class defines the framework for sensor devices,
 * including methods for retrieving the sensor type and formatting sensor data.
 * Subclasses must implement the specific behavior for data handling.
 * </p>
 */
public abstract class Sensor extends Device {

    /**
     * Returns the sensor type with the suffix {@code " Sensor"}.
     *
     * @return the sensor type with the suffix " Sensor"
     */
    @Override
    public String getDevType() {
        return getSensType() + " Sensor"; 
    }

    /**
     * Returns the specific type of the sensor.
     * <p>
     * Implementation must be provided by subclasses.
     * </p>
     *
     * @return the sensor type
     */
    public abstract String getSensType();

    /**
     * Converts sensor data to a string representation.
     * <p>
     * Implementation must be provided by subclasses.
     * </p>
     *
     * @return the sensor data as a {@code String}
     */
    public abstract String data2String();
}
