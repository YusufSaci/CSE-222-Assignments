package HWSystem.Protocols;

/**
 * Defines the contract for communication protocols.
 * <p>
 * This interface specifies the methods that must be implemented by any communication protocol.
 * </p>
 */
public interface Protocol {

    /**
     * Reads data from the protocol.
     *
     * @return the data read from the protocol
     */
    String read();

    /**
     * Writes data to the protocol.
     *
     * @param data the data to be written to the protocol
     */
    void write(String data);

    /**
     * Returns the name of the protocol.
     *
     * @return the name of the protocol
     */
    String getProtocolName();


    void close();
}
