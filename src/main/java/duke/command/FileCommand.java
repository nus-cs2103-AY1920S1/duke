package duke.command;

import duke.*;

/**
 * Encapsulates a command to change file path.
 */
public class FileCommand extends Command {
    protected final String filePath;

    /**
     * Constructs a FileCommand object.
     *
     * @param filePath  Path to saved data file on hard disk.
     */
    public FileCommand(String filePath) {
        super();
        this.filePath = filePath;
    }

    /**
     * Executes the command, loading saved data file.
     *
     * @param duke Chat bot.
     * @param storage  Data file of chat bot.
     * @param taskList  Task list of chat bot.
     * @return Result of command.
     */
    @Override
    public String execute(Duke duke, Storage storage, TaskList taskList) {
        try {
            duke.load(filePath);
        } catch (DukeException e) {
            assert(!isBye());
            return Ui.showError(e.getMessage());
        }
        assert(!isBye());
        return Ui.showLoadMessage(filePath);
    }
}
