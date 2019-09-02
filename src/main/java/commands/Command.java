package commands;

import storage.Storage;
import tasks.TaskList;
import ui.Ui;

public abstract class Command {

    /**
     * Updates the task list, text document file and displays the relevant relevant messages
     * according to the user's command
     * @param taskList consolidated list of tasks
     * @param ui prints the relevant messages according the user's commands
     * @param storage updates the text document file
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage);

    public abstract boolean isExit();
}
