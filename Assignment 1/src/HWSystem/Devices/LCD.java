package HWSystem.Devices;

// LCD class extending Display
public class LCD extends Display {
    
    // Method to turn on the LCD display
    public void turnOn() {
        System.out.println(getName() + ": Turning ON."); // Print message
        getProtocol().write("TURN ON"); // Send command to turn on
        state = State.ON; // Update state to ON
    }

    // Method to turn off the LCD display
    public void turnOff() {
        System.out.println(getName() + ": Turning OFF."); // Print message
        getProtocol().write("TURN OFF"); // Send command to turn off
        state = State.OFF; // Update state to OFF
    }

    // Method to print data on the LCD display
    public void printData(String data) {
        getProtocol().write(data); // Write data to protocol (simulated display output)
    }

    // Method to return the name of the display
    public String getName() {
        return "LCD";
    }
}
