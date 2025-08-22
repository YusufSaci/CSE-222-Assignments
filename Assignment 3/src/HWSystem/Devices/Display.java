package HWSystem.Devices;

/**
 * Represents an abstract display device.
 * <p>
 * This abstract class extends {@code Device} and defines common behaviors 
 * for all display devices, such as printing data.
 * </p>
 */
public abstract class Display extends Device {

    /**
     * Prints or displays data on the device.
     * <p>
     * Implementation must be provided by subclasses.
     * </p>
     *
     * @param data the data to be displayed
     */
    public abstract void printData(String data);

    /**
     * Returns the type of the device as {@code "Display"}.
     *
     * @return the device type, "Display"
     */
    @Override
    public String getDevType() {
        return "Display";
    }
}
