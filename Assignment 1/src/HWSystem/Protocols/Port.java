package HWSystem.Protocols;

import HWSystem.Devices.Device;

// Port class representing a connection point for a device using a specific protocol
public class Port {

    private Protocol protocol;  // Protocol used by the port
    private Device device;      // Device connected to the port

    // Method to get the device connected to the port
    public Device getDevice() {
        return device;
    }

    // Method to get the protocol associated with the port
    public Protocol getProtocol() {
        return protocol;
    }

    // Method to set the protocol for the port
    public void setProtocol(Protocol protocol) {
        this.protocol = protocol;
    }

    // Method to connect a device to the port
    public void connectDevice(Device device) {
        this.device = device;  // Assign the device to the port
    }

    // Method to disconnect the device from the port
    public void disconnectDevice() {
        this.device = null;  // Set device to null, effectively disconnecting it
    }

    // Method to check if the port is empty (no device connected)
    public boolean isEmpty() {
        return device == null;  // Return true if no device is connected
    }
}
