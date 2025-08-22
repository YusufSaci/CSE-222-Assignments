package HWSystem.Devices;

/**
 * Represents a wireless input/output device, extending {@code Device}.
 * <p>
 * This abstract class defines the framework for sending and receiving data over a wireless connection.
 * Subclasses must implement the methods for data transmission and reception.
 * </p>
 */
public abstract class WirelessIO extends Device {

    /**
     * Sends data using the wireless device.
     * <p>
     * Implementation must be provided by subclasses.
     * </p>
     *
     * @param data the data to be sent
     */
    public abstract void sendData(String data);

    /**
     * Receives data using the wireless device.
     * <p>
     * Implementation must be provided by subclasses.
     * </p>
     *
     * @return the data received as a {@code String}
     */
    public abstract String recvData();

    /**
     * Returns the device type as {@code "WirelessIO"}.
     *
     * @return the device type, "WirelessIO"
     */
    @Override
    public String getDevType() {
        return "WirelessIO";
    }
}
