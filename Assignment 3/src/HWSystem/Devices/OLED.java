package HWSystem.Devices;

/**
 * Represents an OLED display.
 * <p>
 * This class extends {@code Display} and provides methods to turn the display on or off, 
 * print data on the display, and retrieve the name of the display.
 * </p>
 */
public class OLED extends Display {

    /**
     * Turns on the OLED display and updates its state to {@code ON}.
     */
    @Override
    public void turnOn() {
        getProtocol().write("turnON");

        System.out.println(getName() + ": Turning ON.");
        state = State.ON; // Update state to ON
    }

    /**
     * Turns off the OLED display and updates its state to {@code OFF}.
     */
    @Override
    public void turnOff() {
        getProtocol().write("turnOFF");

        System.out.println(getName() + ": Turning OFF.");
        state = State.OFF; // Update state to OFF
    }

    /**
     * Prints the given data on the OLED display.
     *
     * @param data the data to be printed on the display
     */
    @Override
    public void printData(String data) {
        getProtocol().write(data);
        System.out.printf("%s: Printing \"%s\".\n", getName(), data);
    }

    /**
     * Returns the name of the display.
     *
     * @return the name of the display, "OLED"
     */
    @Override
    public String getName() {
        return "OLED";
    }
}
