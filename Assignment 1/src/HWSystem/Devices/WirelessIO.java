package HWSystem.Devices;

// Abstract class representing a wireless input/output device, extending Device
public abstract class WirelessIO extends Device {

    // Abstract method to send data (to be implemented by subclasses)
    public abstract void sendData(String data);

    // Abstract method to receive data (to be implemented by subclasses)
    public abstract String recvData();

    // Method to return the device type as "WirelessIO"
    public String getDevType() {
        return "WirelessIO";
    }
}
