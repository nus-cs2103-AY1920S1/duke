package duke.command;

import duke.helper.Storage;
import duke.helper.Ui;
import duke.task.TaskList;

/**
 * Base class for classes that execute logic based on user commands.
 */
public abstract class Command {
    protected String filePath;
    protected String[] inputSplit;

    /**
     * Sets up class attributes common across subclasses of Command.
     *
     * @param inputSplit String array of user input split by spaces.
     * @param filePath Path of save file on hard disk to be written to.
     */
    public Command(String filePath, String[] inputSplit) {
        this.filePath = filePath;
        this.inputSplit = inputSplit;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    public boolean isExit() {
        return false;
    }
}
