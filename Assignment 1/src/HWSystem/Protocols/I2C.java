package HWSystem.Protocols;

// I2C class implementing the Protocol interface
public class I2C implements Protocol {

    // Method to simulate reading data via I2C protocol
    @Override
    public String read() {
        return getProtocolName() + ": Reading.\n"; // Return protocol-specific read message
    }

    // Method to simulate writing data via I2C protocol
    @Override
    public void write(String data) {
        System.out.println(getProtocolName() + ": Writing \"" + data + "\"."); // Print protocol-specific write message
    }

    // Method to return the name of the protocol ("I2C")
    @Override
    public String getProtocolName() {
        return "I2C";
    }
}
