import java.util.Iterator;
import java.util.NoSuchElementException;


public class GTUHashMap <K,V>{
    private Entry<K,V>[] table;
    private int size;
    private int deletedSize;
    private int numOfCollision;

   // Constructor for GTUHashMap
    @SuppressWarnings("unchecked")
    public GTUHashMap() {
        // Initialize the hash table with a default capacity of 11
        table = new Entry[11];
        // Initialize size (number of active entries) to 0
        size = 0;
        // Initialize deletedSize (number of tombstoned entries) to 0
        deletedSize = 0;

        numOfCollision = 0;
    }

    private int find(K key, Boolean isPut) {
      
        int hash = key.hashCode();
        int index = hash % table.length;
        
        if(index < 0) {
            index += table.length;
        }

        int probe = 1; // Probe counter
        int initialIndex = index;

        // Probe through the table until we find either:
        // 1. An empty slot (null)
        // 2. A deleted entry that could be reused
        // 3. The exact key we're looking for
        while(table[index] != null && (table[index].isDeleted==false && !table[index].key.equals(key))) {
            if(isPut){
                numOfCollision++;
            }
            // Quadratic probing
            index = (initialIndex + probe * probe) % table.length;
            probe++;
            
            // If we've probed the entire table and didn't find the key
            if (probe > table.length) {
                return -1; // Indicates table is full and key not found
            }
        }
        
        return index;
    }

    @SuppressWarnings("unchecked")
    private void rehash(){

        Entry<K,V>[] oldTable = table;
            
        // Calculate new capacity as the next prime number 
        int newCapacity = nextPrime(table.length * 2 + 1);
        table = new Entry[newCapacity];

        size=0;
        deletedSize = 0;

        // Reinsert all active entries from old table to new table
        for(int i = 0; i < oldTable.length ; i++){
            if(oldTable[i]!=null && oldTable[i].isDeleted==false){

                // Use put() to properly handle hash distribution in new table
                put(oldTable[i].key,oldTable[i].value,false);
            }
        }
        
    }

    public void put(K key, V value) {
        put(key, value, true); 
    }
    

    public void put(K key,V value , boolean flag){

        int index = find(key, flag);

        // If table is full (find returned -1), rehash and retry
        if (index == -1) {
            rehash();
            put(key, value);
            return;
        }

        // Case 1: Empty slot found - create new entry
        if(table[index]==null){
            table[index] = new Entry<>(key, value);
            size++;

        } 
         // Case 2: Deleted entry found - reuse the slot
        else if(table[index].isDeleted){
            table[index] = new Entry<>(key, value);
            size++;
            deletedSize--;

        }
        
        // Case 3: Existing entry found - update value
        else{
            table[index].value=value;
        }

        // Check if rehashing is needed (including tombstones in load factor)
        double loadFactor = (double) (size + deletedSize) / table.length;
        if(loadFactor >= 0.75){
            rehash();
        }
    }

    public V get(K key){

        int index = find(key,false);

        // If find() returned -1, the table is full and key doesn't exist
        if(index==-1){
            return null;
        }
        // Return the value only if the slot contains a non-deleted entry
        if (table[index] != null && table[index].isDeleted==false) {
            return table[index].value;
        }
        return null; 
    }

    public void remove(K key){
        int index = find(key,false);

        // If find() returned -1, the table is full and key doesn't exist
        if(index==-1){
            return;
        }

        if (table[index] != null && table[index].isDeleted==false) {
            // Mark as deleted (tombstone) 
            table[index].isDeleted=true;
            // Update counters
            deletedSize++;
            size--;
        }
    }

    public boolean containsKey(K key){
        int index = find(key,false);

        // If find() returned -1, the table is full and key doesn't exist
        if(index==-1){
            return false;
        }

        // Only return true for non-null, non-deleted entries
        if (table[index] != null && table[index].isDeleted==false) {
            return true;
        }

        return false;

    }

    public int size(){
        return size;
    }

    public Iterator<K> keyIterator() {
        return new Iterator<K>() {

            // Current position in the table array
            private int index = 0;

            @Override
            public boolean hasNext() {
                // Scan the table until we find a valid entry or reach the end
                while (index < table.length) {
                     // Found a non-null, non-deleted entry
                    if (table[index] != null && !table[index].isDeleted){
                        return true;
                    }
                    // Skip null or deleted entries
                    index++;
                }
                return false;
            }

            @Override
            public K next() {
                if (!hasNext()) throw new NoSuchElementException();
                return table[index++].key;
            }
        };
    }


    private int nextPrime(int n) {
        if (n <= 1) return 2;
        if (n % 2 == 0) n++;
        
        while (!isPrime(n)) {
            n += 2;
        }
        return n;
    }


    // Helper method to check if a number is prime
    private  boolean isPrime(int n) {
        if (n <= 1) return false;
        if (n == 2) return true;
        if (n % 2 == 0) return false;
        
        for (int i = 3; i * i <= n; i += 2) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public int getNumOfCol(){
        return numOfCollision;
    }


}
