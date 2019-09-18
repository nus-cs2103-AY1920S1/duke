package duke.task;

/**
 * Represents a task of type deadline. An <code>Deadline</code> object contains a description,
 * a  boolean representing whether or not the task has been done and additional information.
 */
public class Deadline extends Task {
    protected String  by;

    /**
     * Constructs a new Deadline.
     * @param description description of the task
     * @param isDone whether or not the task has been done
     */
    public Deadline(String description, boolean isDone, String info) {
        super(description, isDone, info);
        super.type = Type.DEADLINE;
        String[] infos = info.split(" ", 2);
        by =  super.checkDate(infos[1]);
    }

    /**
     * Overrides toString method.
     * @return a String in the to-be-displayed format
     */
    @Override
    public String toString() {

        return "[D][" + getStatusIcon() + "] " + description + " (by: " +  by + ")";
    }

    /**
     * Returns a String in the format for file saving.
     * @return a string in the saved format
     */
    @Override
    public String getFileStringFormat() {
        if (isDone()) {
            return "D | 1 | " + description + " | " + info;
        } else {
            return "D | 0 | " + description + " | " + info;
        }
    }
}
