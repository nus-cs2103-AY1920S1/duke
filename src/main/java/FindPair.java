package duke.task;

/**
 * A class used in finding a specific task. Contains the task and whether it is
 * part of the search result.
 */
public class FindPair {
    private Task task;
    private boolean found;

    /**
     * Creates a pair on whether a task is found. The first object is the task while
     * the second object is the status of whether the task description contains the
     * search string.
     * 
     * @param task  the task object
     * @param found the boolean status on whether the string is found in the
     *              description
     */
    public FindPair(Task task, boolean found) {
        this.task = task;
        this.found = found;
    }

    /**
     * Get the status of whether the string is found in the description of the task.
     * 
     * @return boolean value on whether the string is found
     */
    public boolean isFound() {
        return this.found;
    }

    /**
     * Get the task.
     * 
     * @return the task object within the pair
     */
    public Task getTask() {
        return this.task;
    }
}
