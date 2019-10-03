package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.util.Ui;


/**
 * Class representing a command that loads a new task list from a file path
 * Upon execution, the command will attempt to load from the new file path. If successful,
 * the new file location will automatically be used as the default saving path as well.
 *
 * @see Command
 * @see SaveCommand
 */
public class LoadCommand extends Command {

    public static final String KEYWORD = "load";

    private String filePath;

    /**
     * Returns a LoadCommand object.
     *
     * @param filePath file path of the new location.
     */
    public LoadCommand(String filePath) {
        this.filePath = filePath;
    }


    /**
     * Attempts to load the new task list from the given filePath. Upon success, this command
     * changes the save location to the new path as well (and notifies the user of this change).
     * Upon failure, it reverts back to the old save directory.
     *
     * @return result feedback of the command to be printed to the user
     * @throws DukeException if loading was unsuccessful
     */
    @Override
    public String execute() throws DukeException {
        String previousPath = this.storage.getSavePath();
        try {
            // intuitively, users would want to save to same location that
            // the new list was loaded from instead of overwriting the current list.
            this.storage.changeSavePath(this.filePath);
            TaskList loadedList = this.storage.loadFromDisk();
            this.taskList.replaceWith(loadedList);
            return "Got it. I have changed the loading filepath from\n'"
                    + previousPath + "'\nto\n'"
                    + this.filePath + "'\n\n"
                    + (new ListCommand()).initialize(storage, taskList, ui).execute()
                    // remind users that save location was also changed
                    + "\nNOTE: I will also update the save path to the same location.\n"
                    + "If you want to save to a different location, use the 'save' command!";
        } catch (DukeException e) { // upon failure, revert back to old path
            this.storage.changeSavePath(previousPath);
            throw new DukeException("Hmm, I couldn't locate any saved lists in the following file path:\n"
                    + Ui.INDENT + "'" + this.filePath + "'"
                    + "\n\nIn any case, I will continue to use the current file path:\n"
                    + Ui.INDENT + "'" + previousPath + "'",
                    e);
        }
    }
}
