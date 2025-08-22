// A generic class representing a key-value pair (an entry) for use in a hash map or similar data structure.
public class Entry<K, V> { 
    // The key of the entry
    public K key; 
    
    // The value associated with the key
    public V value; 
    
    // A flag to indicate whether the entry has been logically deleted
    public boolean isDeleted; 
    
    // Constructor to initialize the key and value, and set isDeleted to false
    public Entry(K key, V value) { 
        this.key = key; 
        this.value = value; 
        this.isDeleted = false; 
    } 
}