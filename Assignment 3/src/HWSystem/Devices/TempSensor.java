package HWSystem.Devices;

/**
 * Represents a temperature sensor, extending {@code Sensor}.
 * <p>
 * This abstract class defines the framework for temperature sensors,
 * including methods for retrieving the temperature value and formatting sensor data.
 * </p>
 */
public abstract class TempSensor extends Sensor {

    /**
     * Returns the current temperature value.
     * <p>
     * Implementation must be provided by subclasses.
     * </p>
     *
     * @return the current temperature value
     */
    public abstract float getTemp();

    /**
     * Returns the sensor type as {@code "TempSensor"}.
     *
     * @return the sensor type, "TempSensor"
     */
    @Override
    public String getSensType() {
        return "TempSensor";
    }

    /**
     * Returns the sensor data as a formatted string.
     *
     * @return a formatted string representing the temperature data
     */
    @Override
    public String data2String() {
        return String.format("Temperature: %.2fC.", getTemp());
    }
}
