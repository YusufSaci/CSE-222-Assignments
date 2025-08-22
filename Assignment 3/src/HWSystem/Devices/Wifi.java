package HWSystem.Devices;

/**
 * Represents a WiFi device.
 * <p>
 * This class extends {@code WirelessIO} and provides methods to turn the device on or off,
 * send and receive data.
 * </p>
 */
public class Wifi extends WirelessIO {

    /**
     * Turns on the WiFi device and updates its state to {@code ON}.
     */
    @Override
    public void turnOn() {
        getProtocol().write("turnON");

        System.out.println(getName() + ": Turning ON.");
        state = State.ON; // Update state to ON
    }

    /**
     * Turns off the WiFi device and updates its state to {@code OFF}.
     */
    @Override
    public void turnOff() {
        getProtocol().write("turnOFF");

        System.out.println(getName() + ": Turning OFF.");
        state = State.OFF; // Update state to OFF
    }

    /**
     * Returns the name of the WiFi device.
     *
     * @return the name of the device, "Wifi"
     */
    @Override
    public String getName() {
        return "Wifi";
    }

    /**
     * Sends data over the WiFi connection.
     *
     * @param data the data to be sent
     */
    @Override
    public void sendData(String data) {
        getProtocol().write(data);
        System.out.printf("%s: Sending \"%s\".\n", getName(), data);
    }

    /**
     * Receives data via WiFi connection.
     *
     * @return a formatted string indicating received data
     */
    @Override
    public String recvData() {
        getProtocol().read();
        return String.format("%s: Received \"Some Data\".\n", getName());
    }
}
