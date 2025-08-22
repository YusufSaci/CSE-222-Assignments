package HWSystem.Devices;

/**
 * Represents a DHT11 temperature sensor device.
 * <p>
 * This class extends {@code TempSensor} and provides methods to retrieve
 * the temperature value, turn the device on or off, and return the device name.
 * </p>
 */
public class DHT11 extends TempSensor {

    /**
     * Retrieves the current temperature reading from the sensor.
     *
     * @return the current temperature in degrees Celsius
     */
    @Override
    public float getTemp() {
        getProtocol().read();
        return 24f;
    }

    /**
     * Turns on the DHT11 sensor and updates its state to {@code ON}.
     */
    @Override
    public void turnOn() {
        getProtocol().write("turnON");

        System.out.println(getName() + ": Turning ON.");
        state = State.ON; // Update state to ON
    }

    /**
     * Turns off the DHT11 sensor and updates its state to {@code OFF}.
     */
    @Override
    public void turnOff() {
        getProtocol().write("turnOFF");

        System.out.println(getName() + ": Turning OFF.");
        state = State.OFF; // Update state to OFF
    }

    /**
     * Returns the name of the DHT11 sensor.
     *
     * @return the name of the sensor, "DHT11"
     */
    @Override
    public String getName() {
        return "DHT11";
    }
}
