/**
 * An abstract class to instantiate all the Task objects.
 */
public abstract class Task {
    protected String description;
    protected Boolean isdone;

    /**
     * Task object is instantiated when User enters the description of task.
     * @param description Description of tasks.
     */
    public Task (String description) {
        this.description = description;
        this.isdone = false;
    }

    /**
     * For other classes to retrieve the description information.
     * @return Description of task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * For other classes to retrieve the status of task whether if its
     * completed or not.
     * @return Status of task.
     */
    public String getStatusIcon() {
        return (isdone ? "\u2713" : "\u2718");
    }

    /**
     * A method to check the status when saving the tasks into the .txt file.
     * @return Status of task, "1" means its done and "0" means its yet to be completed.
     */
    public String checkStatus() {
        if (isdone == true) {
            return "1";
        } else {
            return "0";
        }
    }

    /**
     * A method to revert the numerical status back to the boolean.
     * @param status The number "1" or "0".
     */
    public void recoverStatus(String status) {
        if (status.equals("1")) {
            isdone = true ;
        } else {
            isdone = false;
        }
    }

    /**
     * A flag to toggle when a task is done.
     */
    public void markasdone() {
        isdone = true;
    }

    /**
     * Format the String into a save file format.
     * @return Formatted String for the .txt file.
     */
    public abstract String formatString();

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + description;
    }

}
