/**
 * Represent user command to to exit the program.
 */

public class ExitCommand extends Command {

    /**
     * Represents an action to close their user interface.
     * @param command Type of task
     */
    public ExitCommand(String command) {
        super(command);
    }

    public void execute(TaskList tasks, UI ui, Storage storage) throws DukeException {
        storage.save(tasks);
    }

    public boolean isExit() {
        return true;
    }

    @Override
    public String toString() {
        return "Bye. Hope to see you again soon!";
    }
}
