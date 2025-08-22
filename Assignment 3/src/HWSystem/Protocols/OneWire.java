package HWSystem.Protocols;

import java.io.File;
import java.io.FileWriter;
import java.util.Stack;

/**
 * Represents the OneWire communication protocol.
 * <p>
 * This class implements the {@code Protocol} interface and simulates
 * reading and writing data using the OneWire protocol.
 * </p>
 */
public class OneWire implements Protocol {
    
    private Stack<String> stack = new Stack<>();
    private File file;

    /**
     * Constructs a {@code OneWire} object with the specified file path and name.
     * <p>
     * If the specified file does not exist, it will be created along with its
     * parent directories if necessary. The message "Port Opened." is pushed 
     * onto the stack when the file is successfully created.
     * </p>
     *
     * @param filePath the directory where the file is located
     * @param fileName the name of the file used for data storage
     */
    public OneWire(String filePath, String fileName) {
        file = new File(filePath, fileName);
        stack.push("Port Opened.\n");
        if (!file.exists()) {
            try {
                // Ensure the parent directory exists
                file.getParentFile().mkdirs();
                file.createNewFile();
            } catch (Exception e) {
                System.err.println("Error creating file: " + e.getMessage());
            }
        }  
    }

    /**
     * Simulates reading data using the OneWire protocol.
     * <p>
     * This method pushes a "Reading." message onto the stack and returns
     * the same message.
     * </p>
     *
     * @return a string message indicating a read operation
     */
    @Override
    public String read() {
        stack.push("Reading.\n");
        return "Reading.\n";
    }

    /**
     * Simulates writing data using the OneWire protocol.
     * <p>
     * The provided data is formatted into a message and pushed onto the stack.
     * </p>
     *
     * @param data the data to be written
     */
    @Override
    public void write(String data) {
        stack.push("Writing \"" + data + "\".\n");
    }

    /**
     * Returns the name of the protocol.
     *
     * @return the protocol name, "OneWire"
     */
    @Override
    public String getProtocolName() {
        return "OneWire";
    }

    /**
     * Closes the OneWire communication and writes all stored messages to the file.
     * <p>
     * This method writes all messages stored in the stack to the file in reverse order.
     * If an error occurs during file writing, an error message is printed.
     * </p>
     */
    public void close() { //AI
        FileWriter writer;
        try {
            writer = new FileWriter(file);

            while (!stack.isEmpty()) {
                writer.write(stack.pop());
            }
            writer.close();

        } catch (Exception e) {
            System.err.println("File Error: " + e.getMessage());
        }
    }
}
