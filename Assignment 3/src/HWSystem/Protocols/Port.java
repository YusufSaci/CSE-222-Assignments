package HWSystem.Protocols;

import HWSystem.Devices.Device;

/**
 * Represents a connection point for a device using a specific protocol.
 * <p>
 * This class manages the association between a device and a communication protocol.
 * </p>
 */
public class Port {

    /** Protocol used by the port. */
    private Protocol protocol;

    /** Device connected to the port. */
    private Device device;

    /**
     * Returns the device connected to the port.
     *
     * @return the device connected to the port, or {@code null} if no device is connected
     */
    public Device getDevice() {
        return device;
    }

    /**
     * Returns the protocol associated with the port.
     *
     * @return the protocol used by the port
     */
    public Protocol getProtocol() {
        return protocol;
    }

    /**
     * Sets the protocol for the port.
     *
     * @param protocol the protocol to be associated with the port
     */
    public void setProtocol(Protocol protocol) {
        this.protocol = protocol;
    }

    /**
     * Connects a device to the port.
     *
     * @param device the device to be connected
     */
    public void connectDevice(Device device) {
        this.device = device;
    }

    /**
     * Disconnects the device from the port.
     */
    public void disconnectDevice() {
        this.device = null;
    }

    /**
     * Checks if the port is empty (no device connected).
     *
     * @return {@code true} if no device is connected, {@code false} otherwise
     */
    public boolean isEmpty() {
        return device == null;
    }
}
