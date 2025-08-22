package HWSystem.Devices;

// Abstract class representing an IMU 
public abstract class IMUSensor extends Sensor {
    protected float accel; // Acceleration value
    protected float rot;   // Rotation value

    // Method to get acceleration value
    public float getAccel() {
        accel = (float) (Math.random()); // Generate a random acceleration value
        System.out.print(getProtocol().read()); // Read from protocol (simulated)
        return accel;
    }

    // Method to get rotation value
    public float getRot() {
        rot = (float) (Math.random()); // Generate a random rotation value
        System.out.print(getProtocol().read()); // Read from protocol (simulated)
        return rot;
    }

    // Method to return the sensor type as "IMUSensor"
    public String getSensType() {
        return "IMUSensor";
    }

    // Method to return sensor data as a formatted string
    public String data2String() {
        return String.format("Accel: %.2f, Rot: %.2f", getAccel(), getRot());
    }
}
