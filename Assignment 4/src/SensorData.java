/**
 * Represents sensor data readings for environmental conditions.
 * 
 * This class stores temperature, pressure, humidity, and radiation values,
 * and provides access to these readings.
 */
public class SensorData {
    private double temperature;
    private double pressure;
    private double humidity;
    private double radiation;

    /**
     * Constructs a SensorData object with the specified sensor readings.
     *
     * @param temperature the temperature value in Kelvin
     * @param pressure the pressure value in Pascals
     * @param humidity the humidity percentage
     * @param radiation the radiation value in Sieverts
     */
    public SensorData(double temperature, double pressure, double humidity, double radiation) {
        this.temperature = temperature;
        this.pressure = pressure;
        this.humidity = humidity;
        this.radiation = radiation;
    }

    /**
     * Returns the radiation value.
     *
     * @return the radiation value in Sieverts
     */
    public double getRadiation() {
        return radiation;
    }

    /**
     * Returns a string representation of the sensor data, including temperature,
     * pressure, humidity, and radiation.
     *
     * @return a formatted string with sensor readings and their units
     */
    @Override
    public String toString() {
        return String.format(" %.2f Kelvin,  %.2f Pascals,  %.2f%%,  %.2f Sieverts.", 
                             temperature, pressure, humidity, radiation);
    }
}