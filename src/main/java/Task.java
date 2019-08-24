public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDone() {
        return isDone;
    }

    public void markAsDone(boolean done) {
        isDone = done;
    }

    public String getStatusWithDescription() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }

    /**
     * Validate the input from the user.
     * @param description Description of the task
     * @param taskName Name of the task e.g. event or deadline
     * @return
     */
    public static boolean validateData(String description, String taskName) {
        if (description.length() <= 0) {
            System.out.println("☹ OOPS!!! The description of a " + taskName + " cannot be empty.");
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getStatusWithDescription();
    }
}