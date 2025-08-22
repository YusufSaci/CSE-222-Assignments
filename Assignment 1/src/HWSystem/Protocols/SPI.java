package HWSystem.Protocols;

// SPI class implementing the Protocol interface
public class SPI implements Protocol {

    // Method to simulate reading data via SPI protocol
    @Override
    public String read() {
        return getProtocolName() + ": Reading.\n"; // Return protocol-specific read message
    }

    // Method to simulate writing data via SPI protocol
    @Override
    public void write(String data) {
        System.out.println(getProtocolName() + ": Writing \"" + data + "\"."); // Print protocol-specific write message
    }

    // Method to return the name of the protocol ("SPI")
    @Override
    public String getProtocolName() {
        return "SPI";
    }
}
