package duke.command;

import duke.TaskList;
import duke.ui.Ui;

public class HelpCommand extends Command {

    /**
     * Show the user the list of commands available.
     *
     * @tasks to access the list of tasks
     * @param ui to give feedback to user
     */
    public String execute(TaskList tasks, Ui ui) {
        return ui.showHelp();
    }
}
