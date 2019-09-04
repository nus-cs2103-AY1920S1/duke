import java.util.InputMismatchException;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    /**
     * genTodoTask() will convert input cmd into Todo Task.
     *
     * @param cmd is the string input by user
     * @return Todo Task
     */
    public static Todo genTodoTask(String cmd) {
        try {
            String desc = cmd.substring(5); //words after todo
            return new Todo(desc);
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeIllegalArgumentException("The description of a Todo cannot be empty.");
        }
    }

    @Override
    public String toDataFormat() {
        return "T | " + super.toDataFormat();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
