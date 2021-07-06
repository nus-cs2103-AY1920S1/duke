package duke.command;

/**
 * A Command which is used to instruct Duke to save a TaskList to a specified file.
 */
public class SaveCommand extends Command {

    /**
     * Constructs the Command which is used to instruct Duke to save the TaskList to the TaskList
     * save file with the specified name.
     *
     * @param fileName The file name of the TaskList save file to save the TaskList to.
     */
    SaveCommand(String fileName) {
        super(Type.COMMAND_SAVE_FILE, fileName);
        assert fileName != null;
    }
}