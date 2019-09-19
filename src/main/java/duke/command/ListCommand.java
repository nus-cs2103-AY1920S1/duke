package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ListCommand extends Command {
    /**
     * Constructs a list command.
     */
    public ListCommand() {

    }

    /**
     * Executes the command.
     *
     * @param tasks Task list containing all tasks.
     * @return String representation of the task list.
     */
    @Override
    public String executeCommand(TaskList tasks, Storage storage) {
        return Ui.showTasks(tasks);
    }
}
