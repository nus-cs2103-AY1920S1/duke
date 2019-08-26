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
    public Todo(String desc) {
        super(desc);
    }

    /**
     * Converts the object to its string form to be printed.
     * @return String printing out the Todo object's description
     *          snd status of completion
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Converts the Todo object to the String form to be saved in file.
     * @return String format of the object
     */
    public String toFileFormat() {
        if (isDone) {
            String format = "T | [✓] | " + taskDesc + "\n";
            return format;
        } else {
            String format = "T | [✗] | " + taskDesc + "\n";
            return format;
        }
    }

    /**
     * Retrieves the time description of the object.
     * @return null because Todo tasks do not have a time description
     */
    public Date getDate() {
        return null;
    }
}
