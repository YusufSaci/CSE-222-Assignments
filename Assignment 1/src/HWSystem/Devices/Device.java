package HWSystem.Devices;

import HWSystem.Protocols.Protocol;

// Abstract class representing a generic device
public abstract class Device {
    private Protocol protocol; // Communication protocol used by the device
    protected State state; // Current state of the device (ON/OFF)
    private int ID; // Unique identifier for the device

    // Constructor initializing the device state to OFF
    public Device(){
        state = State.OFF;
    }

    // Getter method for device ID
    public int getID(){
        return ID;
    }

    // Setter method for device ID
    public void setID(int ID){
       this.ID = ID;
    }
   
    // Abstract method to turn the device on (to be implemented by subclasses)
    public abstract void turnOn();

    // Abstract method to turn the device off (to be implemented by subclasses)
    public abstract void turnOff();

    // Abstract method to get the name of the device
    public abstract String getName();

    // Abstract method to get the type of the device
    public abstract String getDevType();

    // Method to get the current state of the device (ON/OFF)
    public State getState(){
        return state;
    }
 
    // Method to get the communication protocol of the device
    public Protocol getProtocol(){
        return protocol;
    }

    // Method to set the communication protocol for the device
    public void setProtocol(Protocol protocol){
        this.protocol = protocol;
    }
}
