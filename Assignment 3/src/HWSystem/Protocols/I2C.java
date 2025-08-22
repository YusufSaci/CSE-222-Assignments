package HWSystem.Protocols;

import java.io.File;
import java.io.FileWriter;
import java.util.Stack;

/**
 * Represents the I2C communication protocol.
 * <p>
 * This class implements the {@code Protocol} interface and simulates
 * reading and writing data using the I2C protocol.
 * </p>
 */
public class I2C implements Protocol {

    private Stack<String> stack = new Stack<>();
    private File file;

    /**
     * Constructs an {@code I2C} object with the specified file path and name.
     * <p>
     * If the specified file does not exist, it will be created along with its
     * parent directories if necessary.
     * </p>
     *
     * @param filePath the directory where the file is located
     * @param fileName the name of the file used for data storage
     */
    public I2C(String filePath, String fileName) {
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
     * Simulates reading data using the I2C protocol.
     * <p>
     * This method pushes a "Reading" message to the internal stack and returns
     * a predefined read message.
     * </p>
     *
     * @return a protocol-specific read message
     */
    @Override
    public String read() {
        stack.push("Reading.\n");
        return "Reading.\n";
    }

    /**
     * Simulates writing data using the I2C protocol.
     * <p>
     * The provided data is pushed onto the internal stack.
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
     * @return the protocol name, "I2C"
     */
    @Override
    public String getProtocolName() {
        return "I2C";
    }

    /**
     * Closes the I2C communication and writes all stored messages to the file.
     * <p>
     * This method writes all messages stored in the internal stack to the file,
     * ensuring that no data is lost. If an error occurs during file operations,
     * it prints an error message.
     * </p>
     */
    public void close() {
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
