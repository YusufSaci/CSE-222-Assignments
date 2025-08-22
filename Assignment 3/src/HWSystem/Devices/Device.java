package HWSystem.Devices;

import HWSystem.Protocols.Protocol;

/**
 * Represents a generic hardware device.
 * <p>
 * This abstract class defines common properties and behaviors for all devices,
 * including state management, unique identification, and communication protocol settings.
 * Subclasses must implement methods to handle device-specific functionality.
 * </p>
 */
public abstract class Device {

    /** Communication protocol used by the device. */
    private Protocol protocol;

    /** Current state of the device (ON/OFF). */
    protected State state;

    /** Unique identifier for the device. */
    private int ID;

    /** Port ID for the device. */
    private int portID;

    /**
     * Constructs a new {@code Device} with the initial state set to {@code OFF}.
     */
    public Device() {
        state = State.OFF;
    }

    /**
     * Returns the unique identifier of the device.
     *
     * @return the unique device ID
     */
    public int getID() {
        return ID;
    }

    /**
     * Sets the unique identifier for the device.
     *
     * @param ID the unique device ID to set
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * Returns the port ID of the device.
     *
     * @return the port ID
     */
    public int getPortID() {
        return portID;
    }

    /**
     * Sets the port ID for the device.
     *
     * @param portID the port ID to set
     */
    public void setPortID(int portID) {
        this.portID = portID;
    }

    /**
     * Turns on the device.
     * <p>
     * Implementation must be provided by subclasses.
     * </p>
     */
    public abstract void turnOn();

    /**
     * Turns off the device.
     * <p>
     * Implementation must be provided by subclasses.
     * </p>
     */
    public abstract void turnOff();

    /**
     * Returns the name of the device.
     * <p>
     * Implementation must be provided by subclasses.
     * </p>
     *
     * @return the name of the device
     */
    public abstract String getName();

    /**
     * Returns the type of the device.
     * <p>
     * Implementation must be provided by subclasses.
     * </p>
     *
     * @return the device type
     */
    public abstract String getDevType();

    /**
     * Returns the current state of the device (ON/OFF).
     *
     * @return the current state of the device
     */
    public State getState() {
        return state;
    }

    /**
     * Returns the communication protocol of the device.
     *
     * @return the protocol used by the device
     */
    public Protocol getProtocol() {
        return protocol;
    }

    /**
     * Sets the communication protocol for the device.
     *
     * @param protocol the protocol to set for the device
     */
    public void setProtocol(Protocol protocol) {
        this.protocol = protocol;
    }
}
