package HWSystem.Devices;

// Abstract class representing a display device
public abstract class Display extends Device {
    
    // Abstract method to print/display data (to be implemented by subclasses)
    public abstract void printData(String data);

    // Method to return the type of device as "Display"
    public String getDevType() {
        return "Display";
    }
}
