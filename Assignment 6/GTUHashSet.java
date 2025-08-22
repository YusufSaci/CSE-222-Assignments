import java.util.Iterator;


public class GTUHashSet <E>  implements Iterable<E>{
    private static final Object WORD = new Object(); 
    private GTUHashMap<E, Object> map;

    // Constructor initializes the internal map
    public GTUHashSet() { 
        map = new GTUHashMap<>(); 
    } 

    // Adds an element to the set by putting it in the map with the dummy value
    public void add(E element) { 
        map.put(element, WORD); 
    }

    // Removes an element from the set
    public void remove(E element) { 
        map.remove(element); 
    }

    // Checks if the set contains a specific element
    public boolean contains(E element) {
         return map.containsKey(element);
    }

     // Returns the number of elements in the set
    public int size() { 
        return map.size();
    } 

    public int numOfCollision(){
        return map.getNumOfCol();
    }

    // Returns an iterator over the elements in the set
    @Override
    public Iterator<E> iterator() {
        return map.keyIterator();  
    }

    // Converts the set to a string representation like [a, b, c]
    @Override
    public String toString(){

        Iterator<E> iterator = iterator();
        StringBuilder str = new StringBuilder();
        
        while(iterator.hasNext()){
            str.append(iterator.next());
            if(iterator.hasNext())
            str.append(", ");
        }
        return String.format("[%s]",str.toString());
    }
 
}
