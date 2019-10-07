package duke.task;

/**
 * Represents a Task user inputs in Duke.
 */

public abstract class Task {
    /**
     * Represents the name of the Task.
     */
    private String name;

    /**
     * Indicates whether Task is done.
     */
    private boolean isDone;

    /**
     * Constructor of Task.
     *
     * @param name Sets name of Task to input.
     */
    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    /**
     * Accessor method for the Task's name.
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Accessor to check if Task is done.
     *
     * @return
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Sets Task isDone to input.
     *
     * @param done Sets isDone variable to input.
     */
    public void setDone(boolean done) {
        this.isDone = done;
    }

    /**
     * Sets Task isDone to done.
     */
    public void done() {
        isDone = true;
    }

    /**
     * Returns Gives appropriate representation of the Task.
     *
     * @return String representation of Task. Includes isDone and name of Task.
     */
    @Override
    public String toString() {
        StringBuilder toPrint = new StringBuilder();
        if (isDone) {
            toPrint.append("[+] ");
        } else {
            toPrint.append("[ ] ");
        }
        toPrint.append(name);
        return toPrint.toString();
    }
}

