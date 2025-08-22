package HWSystem.Devices;

/**
 * Represents a BME280 temperature sensor device.
 * <p>
 * This class extends {@code TempSensor} and provides methods to retrieve
 * the temperature value, turn the device on or off, and return the device name.
 * </p>
 */
public class BME280 extends TempSensor {

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
     * Turns on the BME280 device and updates its state to {@code ON}.
     */
    @Override
    public void turnOn() {
        getProtocol().write("turnON");

        System.out.println(getName() + ": Turning ON.");
        state = State.ON; // Update state to ON
    }

    /**
     * Turns off the BME280 device and updates its state to {@code OFF}.
     */
    @Override
    public void turnOff() {
        getProtocol().write("turnOFF");

        System.out.println(getName() + ": Turning OFF.");
        state = State.OFF; // Update state to OFF
    }

    /**
     * Returns the name of the BME280 device.
     *
     * @return the name of the device, "BME280"
     */
    @Override
    public String getName() {
        return "BME280";
    }
}
