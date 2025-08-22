package HWSystem.Protocols;

// UART class implementing the Protocol interface
public class UART implements Protocol {

    // Method to simulate reading data via UART protocol
    @Override
    public String read() {
        return getProtocolName() + ": Reading.\n"; // Return protocol-specific read message
    }

    // Method to simulate writing data via UART protocol
    @Override
    public void write(String data) {
        System.out.println(getProtocolName() + ": Writing \"" + data + "\"."); // Print protocol-specific write message
    }

    // Method to return the name of the protocol ("UART")
    @Override
    public String getProtocolName() {
        return "UART";
    }
}
