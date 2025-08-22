package MyTaskManager;

/**
 * Represents a task assigned to a user, identified by a unique ID.
 * Tasks are comparable based on user priority and task ID.
 */
public class Task implements Comparable<Task> {

    private User user;      // The user associated with the task
    private Integer id;     // Unique task ID

    /**
     * Constructs a Task with the given user and ID.
     *
     * @param user the user to whom the task is assigned
     * @param id   the unique identifier of the task
     * Time complexity:O(1)
     */
    public Task(User user, Integer id){
        this.user = user;
        this.id = id;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return a string in the format "Task {id} User {userID}"
     * Time complexity: O(1)
     */
    @Override
    public String toString(){
        return String.format("Task %d User %d", id, user.getID());
    }

    /**
     * Compares this task with another based on user priority and task ID.
     * Tasks with higher-priority users come first; if priorities are equal,
     * tasks are ordered by their IDs.
     *
     * @param other the task to compare with
     * @return a negative integer, zero, or a positive integer as this task
     *         is less than, equal to, or greater than the specified task
     * Time complexity:  O(1)
     */
    @Override
    public int compareTo(Task other) {
        // Compare by user priority first
        int priority = this.user.getPriority().compareTo(other.user.getPriority());
        
        if(priority != 0 ){
            return priority; // If different priorities, return result
        }

        // If priorities are equal, compare by task ID
        return this.id.compareTo(other.id);
    }

}
