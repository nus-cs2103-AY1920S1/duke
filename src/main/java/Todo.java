import java.util.Date;

/**
 * Represents a "Todo" task inputted by the user.
 */
public class Todo extends Task {
    /**
     * Constructs a Todo task object.
     * @param desc description of the task by user
     * @throws DukeException if task description is not inputted
     */
    public Todo(String type, String desc) {
        super(type, desc);
    }

    /**
     * Converts the object to its string form to be printed.
     * @return String printing out the Todo object's description
     *          and status of completion
     */
    @Override
    public String toString() {
        return super.toString();
    }

    /**
     * Converts the Todo object to the String form to be saved in file.
     * @return String format of the object
     */
    public String toFileFormat() {
        if (isDone) {
            return type + " | [\u2713] | " + taskDesc + "\n";
        } else {
            return type + " | [\u274C] | " + taskDesc + "\n";
        }
    }

    public Date getDate() {
        return null;
    }
}
