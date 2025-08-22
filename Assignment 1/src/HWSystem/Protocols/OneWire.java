package HWSystem.Protocols;

// OneWire class implementing the Protocol interface
public class OneWire implements Protocol {

    // Method to simulate reading data via OneWire protocol
    @Override
    public String read() {
        return getProtocolName() + ": Reading.\n"; // Return protocol-specific read message
    }

    // Method to simulate writing data via OneWire protocol
    @Override
    public void write(String data) {
        System.out.println(getProtocolName() + ": Writing \"" + data + "\"."); // Print protocol-specific write message
    }

    // Method to return the name of the protocol ("OneWire")
    @Override
    public String getProtocolName() {
        return "OneWire";
    }
}
