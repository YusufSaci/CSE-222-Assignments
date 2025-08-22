package MyTaskManager;

/**
 * Represents a user with a unique ID and a priority level.
 * Priority determines task scheduling order.
 */
public class User {
    
    private Integer id;        // Unique user ID
    private Integer priority;  // User's priority (lower means higher priority in min-heap)

    /**
     * Constructs a user with the given ID and priority.
     *
     * @param id       the unique identifier of the user
     * @param priority the user's priority level
     * Time complexity: O(1)
     */
    public User(Integer id, Integer priority) {
        this.id = id;
        this.priority = priority;
    }

    /**
     * Returns the ID of the user.
     *
     * @return the user ID
     * Time complexity: O(1)
     */
    public Integer getID() {
        return this.id;
    }

    /**
     * Returns the priority level of the user.
     *
     * @return the user's priority
     * Time complexity: O(1)
     */
    public Integer getPriority() {
        return this.priority;
    }

}
