package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.text.ParseException;

/**
 * This is the superclass for Command.
 */

public class Command {

    protected boolean isExit;

    public Command() {
        isExit = false;
    }

    /**
     * This method executes the command
     * @param tasks this is the list of tasks.
     * @param ui this is the ui.
     * @param storage this is the storage.
     * @throws ParseException when there is an invalid command.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return "This shouldn't appear.";
    }

    /**
     * This checks whether the program should terminate.
     * @return true when bye command is entered.
     */
    public boolean isExit() {
        return isExit;
    }
}