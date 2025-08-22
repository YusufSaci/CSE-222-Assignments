import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Manages a planetary system consisting of a star, planets, and satellites (moons).
 * Allows for adding nodes, validating sensor data, finding nodes, reporting anomalies,
 * generating paths, and printing mission reports.
 */
public class PlanetSystem {
    Node star;

    /**
     * Checks if the given sensor data values are within valid ranges.
     *
     * @param humidity  humidity percentage (0–100)
     * @param temperature  temperature in Kelvin (≥ 0)
     * @param pressure  pressure in Pascals (≥ 0)
     * @param radiation  radiation level in Sieverts (≥ 0)
     * @return true if all values are within range, false otherwise
     */
    private boolean checkRange(double humidity, double temperature, double pressure, double radiation){
        if(humidity<0.0 || humidity >100.0 || temperature<0.0 || pressure<0.0 || radiation<0 ){
            return false;
        }
        return true;
    }

    /**
     * Creates the main star (root) of the planetary system.
     *
     * @param name        name of the star
     * @param temperature temperature in Kelvin
     * @param pressure    pressure in Pascals
     * @param humidity    humidity (must be 0)
     * @param radiation   radiation level in Sieverts
     * @return true if creation was successful, false otherwise
     */
    public boolean createPlanetSystem(String name, double temperature,double pressure, double humidity, double radiation){
        if (humidity != 0) {
            System.err.println("Humidity must be 0!");
            return false ;
        }
        else if(!checkRange(humidity,temperature,pressure,radiation)){
            System.err.println("Data of Planet System out of range.Can not created.");
            return false;
        }
        else{
            star = new Node(name,"Star",temperature,pressure,humidity,radiation);
        }
        return true;
    }

    /**
     * Recursively searches for a node with the given name.
     *
     * @param current the starting node
     * @param name    name of the node to find
     * @return the node if found, or null otherwise
     */
    private Node findNode (Node current ,String name){
        if(current.getName().equals(name)){
            return current;
        }
        for(Node child : current.getChildren()){
           Node result = findNode(child, name);
           if(result!=null){
            return result;
           }
        }
        return null;
    }

    /**
     * Adds a new planet to a specified parent node.
     *
     * @param planetName  name of the planet
     * @param parentName  name of the parent node
     * @param temperature temperature of the planet
     * @param pressure    pressure of the planet
     * @param humidity    humidity of the planet
     * @param radiation   radiation of the planet
     */
    public void addPlanet(String planetName , String parentName, double temperature,double pressure, double humidity, double radiation ){
        Node parent = findNode(star, parentName);
        if(parent == null){
            System.err.println("Parent node not found");
        }
        else if(!checkRange(humidity,temperature,pressure,radiation)){
            System.err.println("Data out of range.");
        }
        else if(parent.getType().equals("Moon")){
            System.err.println( "This is a satellite. A planet cannot be added to this satellite.");
        }
        else if(findNode(star, planetName)!=null && findNode(star, planetName).getType().equals("Planet")){
            System.err.println("This planet already exists.");
        }
        else if(checkPlanet(parent)){
            System.err.println("The planets cannot be equidistant from the star.");
        }
        else{
            System.out.println("Planet added.");
            parent.addChild(new Node(planetName,"Planet",temperature,pressure,humidity,radiation));
        }
    }

    /**
     * Checks if the node already has a planet as a child.
     *
     * @param node the node to check
     * @return true if the node has a planet child, false otherwise
     */
    private boolean checkPlanet(Node node){
        boolean flag = false;
        for(Node child : node.getChildren()){
            if(child.getType().equals("Planet")){
                flag = true;
            }
        }
        return flag;
    }

    /**
     * Adds a satellite (moon) to a specified planet.
     *
     * @param satelliteName name of the satellite
     * @param parentName    name of the parent planet
     * @param temperature   temperature of the satellite
     * @param pressure      pressure of the satellite
     * @param humidity      humidity of the satellite
     * @param radiation     radiation of the satellite
     */
    public void addSatellite(String satelliteName , String parentName, double temperature,double pressure, double humidity, double radiation ){
        Node parent = findNode(star, parentName);
        if(parent == null){
            System.err.println("Parent node not found");
        }
        else if(!checkRange(humidity,temperature,pressure,radiation)){
            System.err.println("Data out of range.");
        }
        else if(!parent.getType().equals("Planet")){
            System.err.println( "Parent type must be planet.");
        }
        else if(findNode(star,satelliteName)!=null && findNode(star,satelliteName).getType().equals("Moon")){
            System.err.println("This satellite already exists.");
        }
        else{
            System.out.println("Satellite added.");

            parent.addChild(new Node(satelliteName,"Moon",temperature,pressure,humidity,radiation));
        }
    }

    /**
     * Finds all nodes in the system with radiation above the specified threshold.
     *
     * @param threshold radiation threshold
     * @return list of nodes with radiation above the threshold
     */
    public List<Node> findRadiationAnomalies(double threshold){
        List<Node> anomalies = new ArrayList<>();
        findRadiationAnomaliesR(star, threshold , anomalies);
        return anomalies;
    }

    /**
     * Recursive helper to find radiation anomalies.
     *
     * @param current   the current node
     * @param threshold radiation threshold
     * @param anomalies list to store found anomalies
     */
    private void findRadiationAnomaliesR(Node current, double threshold, List<Node> anamolies){
        if(current.getSensorData().getRadiation()>threshold){
            anamolies.add(current);
        }
        for(Node temp : current.getChildren()){
            findRadiationAnomaliesR(temp, threshold, anamolies);
        }
    }

    /**
     * Finds the path from the star to the specified node.
     *
     * @param name target node name
     * @return stack representing the path from root to the node, or null if not found
     */
    public Stack<String> getPathTo(String name){
        Stack<String> path = new Stack<>();
        if(findPath(star, name, path)){
            return path;
        }
        return null;
    }

    /**
     * Recursive helper to find the path to a specific node.
     *
     * @param current current node
     * @param name    target node name
     * @param path    stack used to build the path
     * @return true if the node was found, false otherwise
     */
    private boolean findPath(Node current, String name, Stack<String> path){
        if(current.getName().equals(name)){
            path.push(current.getName());
            return true;
        }
        for(Node child : current.getChildren()){
            if(findPath(child, name, path)){
                path.push(current.getName());
                return true;
            }
        }
        return false;
    }

    /**
     * Prints the mission report for the entire planetary system.
     */
    public void printMissionReport(){
        printMissionReportR(star);
    }

    /**
     * Recursive helper for printing the mission report.
     *
     * @param current current node being printed
     */
    private void printMissionReportR(Node current){
        System.out.println(current);
        for(Node child : current.getChildren()){
            printMissionReportR(child);
        }
    }

    /**
     * Prints the mission report for a specific node by name.
     *
     * @param name the name of the node to report
     */
    public void printMissionReport(String name){
        Node result = findNode(star, name);
        if(result!=null){
            System.out.println(result);
        }
        else{
            System.err.println("Node not found.");
        }
    }

    

}