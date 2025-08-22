package HWSystem.Devices;

public class Bluetooth extends WirelessIO {

    // Method to turn on the Bluetooth device
    public void turnOn(){
        System.out.println(getName()+": Turning ON." );
        getProtocol().write("TURN ON"); // Send command to turn on
        state=State.ON; // Update state to ON
    }

    // Method to turn off the Bluetooth device
    public void turnOff(){
        System.out.println(getName()+": Turning OFF." );
        getProtocol().write("TURN OFF"); // Send command to turn off
        state=State.OFF; // Update state to OFF
    }

    // Method to return the name of the device
    public String getName(){
        return "Bluetooth";
    }

    // Method to send data via Bluetooth
    public void sendData(String data){
        getProtocol().write(data);  
    }

    // Method to receive data via Bluetooth
    public String recvData(){ 
        return  getProtocol().read(); 
    }

}

