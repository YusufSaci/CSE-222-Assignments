import java.util.ArrayList;
import java.util.List;

/**
 * Represents a node in a sensor network, containing information about the node's
 * name, type, sensor data, and any child nodes.
 * 
 * Each node can store sensor readings (temperature, pressure, humidity, radiation)
 * and maintain a list of its child nodes, allowing for a hierarchical structure.
 */
public class Node {
    private String name;
    private String type;
    private SensorData data;
    private List<Node> children;

    /**
     * Constructs a Node with the specified name, type, and sensor readings.
     *
     * @param name the name of the node
     * @param type the type/category of the node
     * @param temperature the temperature reading
     * @param pressure the pressure reading
     * @param humidity the humidity reading
     * @param radiation the radiation reading
     */
    public Node(String name, String type, double temperature, double pressure, double humidity, double radiation) {
        data = new SensorData(temperature, pressure, humidity, radiation);
        this.name = name;
        this.type = type;
        children = new ArrayList<>();
    }

    /**
     * Returns the sensor data associated with this node.
     *
     * @return the {@link SensorData} object containing sensor readings
     */
    public SensorData getSensorData() {
        return data;
    }

    /**
     * Adds a child node to this node.
     *
     * @param child the {@link Node} to be added as a child
     */
    public void addChild(Node child) {
        children.add(child);
    }

    /**
     * Returns the list of child nodes.
     *
     * @return a list of {@link Node} objects that are children of this node
     */
    public List<Node> getChildren() {
        return children;
    }

    /**
     * Returns the type of the node.
     *
     * @return a string representing the node's type
     */
    public String getType() {
        return type;
    }

    /**
     * Returns the name of the node.
     *
     * @return a string representing the node's name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns a string representation of the node, including its name, type,
     * and sensor data.
     *
     * @return a formatted string describing the node
     */
    @Override
    public String toString() {
        return String.format("%s (%s): %s", name, type, data);
    }
}