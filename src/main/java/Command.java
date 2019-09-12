import java.util.HashMap;

/**
 * Abstract class to represent a command to be executed when an input is entered into a Duke object.
 */
public abstract class Command {

    public abstract String execute(TaskList tasks, Ui ui, TaskStorage taskStorage, ArchiveStorage archiveStorage,
                                   HashMap<String, TaskList> archives) throws InvalidCommandDukeException;

    /**
     * Indicates the exit condition of the running Duke object.
     *
     * @return false by default, only overridden in ExitCommand to return true.
     */
    public boolean isExit() {
        return false;
    }

}
