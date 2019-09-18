import java.util.Date;

public class FixedDurationTask extends Task {
    private String duration;
    /**
     * Constructs a Todo task object.
     * @param desc description of the task by user
     * @throws DukeException if task description is not inputted
     */
    public FixedDurationTask(String type, String desc, String duration) {
        super(type, desc);
        this.duration = duration;
    }


    /**
     * Converts the object to its string form to be printed.
     * @return String printing out the Todo object's description
     *          and status of completion
     */
    @Override
    public String toString() {
        return super.toString() + " (needs: " + this.duration + ")";
    }

    /**
     * Converts the Todo object to the String form to be saved in file.
     * @return String format of the object
     */
    public String toFileFormat() {
        if (isDone) {
            return type + " | [\u2713] | " + taskDesc + " | " + duration + "\n";
        } else {
            return type + " | [\u274C] | " + taskDesc + " | " + duration + "\n";
        }
    }

    public Date getDate() {
        return null;
    }
}
