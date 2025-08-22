package HWSystem.Devices;

// Abstract class representing a sensor device, extending Device
public abstract class Sensor extends Device {

    // Override method to return the sensor type with "Sensor" appended
    @Override
    public String getDevType() {
        return getSensType() + " Sensor"; // Return the sensor type with " Sensor" suffix
    }

    // Abstract method to return the sensor type (to be implemented by subclasses)
    public abstract String getSensType();

    // Abstract method to convert sensor data to a string (to be implemented by subclasses)
    public abstract String data2String();
}
