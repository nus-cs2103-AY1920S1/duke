import java.util.InputMismatchException;

public class Todo extends Task {

    /**
     * Constructs a Todo with description.
     *
     * @param description of the Task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Factory method to construct Todo given the user input.
     *
     * @param cmd the string input by user.
     * @return the Todo object.
     */
    public static Todo genTodoTask(String cmd) {
        try {
            String desc = cmd.substring(5); //words after todo
            return new Todo(desc);
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeIllegalArgumentException("The description of a Todo cannot be empty.");
        }
    }

    /**
     * Stringifies the Deadline in the format specified when writing to storage file.
     *
     * @return string that is to be written to storage file.
     */
    @Override
    public String toDataFormat() {
        return "T | " + super.toDataFormat();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
