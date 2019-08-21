public class Task {
    private boolean isDone;
    private String details;

    protected Task(String details) {
        this.isDone = false;
        this.details = details;
    }

    /**
     * Indicate that the task is done.
     */
    protected void setDone() {
        this.isDone = true;
    }

    /**
     * String representing the Task object.
     * @return String representation of the Task object.
     */
    @Override
    public String toString() {
        char statusIcon = isDone ? (char) 0x2713 : (char) 0x2718;
        return "[" + statusIcon + "] " + details;
    }
}
