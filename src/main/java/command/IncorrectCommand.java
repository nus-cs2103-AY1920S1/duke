package command;

import parser.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Represents an invalid command.
 */
public class IncorrectCommand extends Command {
    /**
     * Prints command is invalid message.
     *
     * @param tasks contains task list
     * @param ui deals with interaction with users
     * @param storage deals with loading and saving of task list
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showIncorrectCommand();
    }
}
