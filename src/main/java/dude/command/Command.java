package dude.command;

import dude.DudeException;
import dude.helper.Storage;
import dude.helper.Ui;
import dude.task.TaskList;

/**
 * Base class for classes that execute logic based on user commands.
 */
public abstract class Command {
    protected String filePath;
    protected String[] inputSplit;

    /**
     * Sets up class attributes common across subclasses of Command.
     *
     * @param filePath Path of save file on hard disk to be written to.
     * @param inputSplit String array of user input split by spaces.
     */
    public Command(String filePath, String[] inputSplit) {
        this.filePath = filePath;
        this.inputSplit = inputSplit;
    }

    public Command() {
    }

    public Command(String[] inputSplit) {
        this.inputSplit = inputSplit;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DudeException;
}
