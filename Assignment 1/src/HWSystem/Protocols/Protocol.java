package HWSystem.Protocols;

// Protocol interface defining the contract for communication protocols
public interface Protocol {

    // Method to read data from the protocol
    String read();

    // Method to write data to the protocol
    void write(String data);

    // Method to return the name of the protocol
    String getProtocolName();
}
