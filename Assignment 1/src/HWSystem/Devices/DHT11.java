package HWSystem.Devices;

// DHT11 class extending TempSensor 
public class DHT11 extends TempSensor {

    // Method to turn on the DHT11 sensor
    public void turnOn() {
        System.out.println(getName() + ": Turning ON."); // Print message
        getProtocol().write("TURN ON"); // Send command to turn on
        state = State.ON; // Update state to ON
    }

    // Method to turn off the DHT11 sensor
    public void turnOff() {
        System.out.println(getName() + ": Turning OFF."); // Print message
        getProtocol().write("TURN OFF"); // Send command to turn off
        state = State.OFF; // Update state to OFF
    }

    // Method to return the name of the sensor
    public String getName() {
        return "DHT11";
    }
}
