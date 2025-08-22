package HWSystem.Devices;

// Wifi class extending WirelessIO 
public class Wifi extends WirelessIO {

    // Method to turn on the Wifi device
    public void turnOn() {
        System.out.println(getName() + ": Turning ON."); // Print message
        getProtocol().write("TURN ON"); // Send command to turn on
        state = State.ON; // Update state to ON
    }

    // Method to turn off the Wifi device
    public void turnOff() {
        System.out.println(getName() + ": Turning OFF."); // Print message
        getProtocol().write("TURN OFF"); // Send command to turn off
        state = State.OFF; // Update state to OFF
    }

    // Method to return the name of the Wifi device
    public String getName() {
        return "Wifi";
    }

    // Method to send data over the Wifi device
    public void sendData(String data) {
        getProtocol().write(data); // Send the formatted data over protocol
    }

    // Method to receive data from the Wifi device
    public String recvData() {
        return getProtocol().read(); // Read and return received data from protocol
    }
}
