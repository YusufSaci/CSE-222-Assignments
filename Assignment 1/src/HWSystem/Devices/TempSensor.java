package HWSystem.Devices;

// Abstract class representing a Temperature Sensor, extending Sensor
public abstract class TempSensor extends Sensor {

    protected float temp; // Variable to store temperature value

    // Method to get temperature value (simulated with random value)
    public float getTemp() {
        temp = (float) (Math.random()); // Generate a random temperature value
        System.out.print(getProtocol().read()); // Read from protocol (simulated)
        return temp;
    }

    // Method to return the sensor type as "TempSensor"
    public String getSensType() {
        return "TempSensor";
    }

    // Method to return sensor data as a formatted string
    public String data2String() {
        return String.format("Temperature: %.2f", getTemp()); // Format and return temperature data
    }
}
