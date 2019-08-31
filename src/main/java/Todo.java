/**
 * Todos are tasks without any date/time attached to it.
 */
public class Todo extends Task {

    /**
     * Creates a todo task with a specific description.
     *
     * @param description Task to do.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Parses the command given to Duke and creates a Todo task if possible.
     *
     * @param fullCommand Full command split by the word "todo".
     * @return Todo task created.
     * @throws DukeException If the description of the todo is empty.
     */
    public static Todo parse(String[] fullCommand) throws DukeException {
        if (fullCommand.length == 1) {
            throw new DukeException("     OOPS!!! The description of a todo cannot be empty :-(");
        }
        return new Todo(fullCommand[1]);
    }

    /**
     * Formats the todo to be stored in the hard disk.
     *
     * @return Todo details in the format T | 1 or 0 | description.
     */
    @Override
    public String toSave() {
        return "T | " + super.getBinaryStatus() + " | " + super.description;
    }

    /**
     * Formats the todo to be displayed to the user.
     *
     * @return Todo details in the format [T][v or x] description.
     */
    @Override
    public String toString() {
        return "[T][" + super.getStatusIcon() + "] " + super.description;
    }

}