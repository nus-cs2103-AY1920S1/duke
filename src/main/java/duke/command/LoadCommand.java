package duke.command;

/**
 * A Command which is used to instruct Duke to load a TaskList from a specified file.
 */
public class LoadCommand extends Command {

    /**
     * Constructs the Command which is used to instruct Duke to load a TaskList the TaskList save file
     * with the specified name.
     *
     * @param fileName The file name of the TaskList save file to load a TaskList from.
     */
    LoadCommand(String fileName) {
        super(Type.COMMAND_LOAD_FILE, fileName);
        assert fileName != null;
    }
}