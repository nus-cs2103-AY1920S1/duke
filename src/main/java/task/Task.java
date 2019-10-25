package task;

/**
 * An Abstract Class extended by all the Task type classes.
 *
 * <p></p>Each Task contains a name and an isDone attribute. Two abstract methods (toFile() and toString()) are meant
 * to be implemented by any classes that extend the Task abstract class.
 */
public abstract class Task {
    /**
     * Indicates where the current task has been completed.
     */
    protected boolean isDone;
    protected String name;


    public Task() {

    }

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    /**
     * Indicates that this Task has already been completed.
     */
    public void setDone() {
        isDone = true;
    }

    /**
     * Returns the name of the Task.
     *
     * @return String which is the name of the Task.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns a String representing the Task in a format to be saved into the hard disk's storage file.
     *
     * <p></p>To be implemented by each individual Task class.
     *
     * @return A String representation of this Task formatted for the storage file.
     */
    public abstract String toFile();

    /**
     * Returns a String representing the Task in a format to be printed into the console.
     *
     * <p></p>To be implemented by each individual Task class.
     *
     * @return A String representation of this Task formatted for printing into the console.
     */
    @Override
    public abstract String toString();
}
