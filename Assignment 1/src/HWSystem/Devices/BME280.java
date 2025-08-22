package HWSystem.Devices;

public class BME280  extends TempSensor{

    // Method to turn on the BME280 device
    public void turnOn(){
        System.out.println(getName()+": Turning ON." );
        getProtocol().write("TURN ON");
        state=State.ON; // Update state to ON
    }

    // Method to turn off the BME280 devic
    public void turnOff(){
        System.out.println(getName()+": Turning OFF." );
        getProtocol().write("TURN OFF");
        state=State.OFF; // Update state to OFF
    }
    
    // Method to return the name of the device
    public String getName(){
        return "BME280";
    }
}

