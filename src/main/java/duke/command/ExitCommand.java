package duke.command;

import duke.task.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * ExitCommands represent a user command to exit the program.
 */
public class ExitCommand extends Command {
    public ExitCommand() {
        super();
        isExit = true;
    }

    /**
     * Shows an exit message.
     * @param tasks a TaskList that stores the list of tasks
     * @param ui a TaskList that stores the list of tasks
     * @param storage Storage object to save task changes to text file
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showExit();
    }
}
