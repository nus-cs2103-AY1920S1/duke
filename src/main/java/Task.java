import java.io.Serializable;

import java.time.format.DateTimeFormatter;

/**
 * Represents a Task given by the User that is parsed by the parser to store information
 * that can be easily printed out in readable format. 
 */
class Task implements Serializable{
    protected static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    protected static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    private static final String TICK = "\u2713";
    private static final String CROSS = "\u2717";

    private String task;
    private boolean isCompleted;

    /**
     * Constructor for Task. 
     * 
     * @param task A String of the user's inputted task
     */
    public Task(String task) {
        this.task = task;
        this.isCompleted = false;
    }
    
    /**
     * Returns isCompleted boolean.
     * 
     * @return isCompleted boolean.
     */
    public boolean hasCompleted() {
        return this.isCompleted;
    }

    /**
     * A method for setting isCompleted boolean.
     * 
     * @param isCompleted Sets the isCompleted boolean to the given boolean
     */
    public void setCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    /**
     * A method for getting the task itself in String form
     * 
     * @return A task itself in String form
     */
    public String getTaskString() {
        return this.task;
    }

    /**
     * Returns the String for the task, including whether it is completed,
     * using ticks and crosses for indication.
     * 
     * @return A String for the task.
     */
    @Override
    public String toString() {
        if (this.isCompleted) {
            return "[" + TICK + "] " + this.task;
        } else {
            return "[" + CROSS + "] " + this.task;
        }
    }
}