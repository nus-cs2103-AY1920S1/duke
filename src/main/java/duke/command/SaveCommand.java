package duke.command;

import duke.exception.DukeException;
import duke.util.Ui;

/**
 * Class representing a command that changes the save file location.
 * Upon execution, will change the save file location then attempt to save to it.
 * If new path fails to be saved to,
 *
 * @see Command
 */
public class SaveCommand extends Command {

    public static final String KEYWORD = "save";

    private String filePath;

    /**
     * Returns a SaveCommand object that will attempt to change the current
     * save location when executed.
     *
     * @param filePath file path of the new location.
     */
    public SaveCommand(String filePath) {
        this.filePath = filePath;
    }


    /**
     * Changes the current saving directory in the given Storage object for Duke.
     * Upon failure, reverts back to the last working directory.
     *
     * @return result feedback of the command to be printed to the user
     * @throws DukeException if saving to new location fails
     */
    @Override
    public String execute() throws DukeException {
        String previousPath = this.storage.getSavePath();

        try {
            previousPath = this.storage.changeSavePath(filePath);
            this.storage.saveToDisk(this.taskList); // attempt to save to new location
            return "Got it. I've saved and changed the current save file path from\n'"
                    + previousPath
                    + "'\nto\n'"
                    + this.filePath + "'";

        } catch (DukeException originalError) { // upon failure, revert back to last working path
            this.storage.changeSavePath(previousPath);
            // then continue to pass the exception upwards
            throw new DukeException("I'm so sorry! I couldn't save to the following filepath:\n"
                    + Ui.INDENT + "'" + this.filePath + "'"
                    + "\n\nI'm reverting back to the previous file path used\n"
                    + Ui.INDENT + "'" + previousPath + "'",
                    originalError);
        }
    }
}
