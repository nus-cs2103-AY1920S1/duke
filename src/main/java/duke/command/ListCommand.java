package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.MainWindow;
import duke.task.Task;

/**
 * Abstraction of the list command for displaying all tasks in the task list.
 */
class ListCommand extends Command {
    /**
     * Constructor of the List command.
     * Calls its parent constructor then sets its commandType enum.
     *
     * @param commandArgs String array of arguments.
     */
    ListCommand(String[] commandArgs) {
        super(commandArgs);
        commandType = Commands.LIST;
    }

    /**
     * Displays the tasks in the task list.
     *
     * @param tasks TaskList of tasks to use.
     * @param ui MainWindow to use for displaying command output.
     * @param storage Storage for WritableCommands to execute write-to-disk operations.
     */
    @Override
    void run(TaskList tasks, MainWindow ui, Storage storage) {
        StringBuilder displayMessage = new StringBuilder(
                "Here are the matching tasks in your list:\n");

        int taskIndex = 1;
        for (Task task : tasks.getAllTasks()) {
            displayMessage.append(String.format("%d.%s\n", taskIndex, task.getStatusText()));
            taskIndex++;
        }
        ui.showMessage(displayMessage.toString());
    }

    /**
     * Checks that there are no other arguments provided to the command.
     *
     * @param tasks TaskList of tasks to use.
     * @param ui MainWindow to use for displaying command output.
     * @param storage Storage for WritableCommands to execute write-to-disk operations.
     * @throws DukeInvalidArgumentException If there are other arguments.
     */
    @Override
    void validate(TaskList tasks, MainWindow ui, Storage storage) throws DukeInvalidArgumentException {
        if (commandArgs.length > 0) {
            throw new DukeInvalidArgumentException(
                    "Encountered extraneous arguments after list command",
                    " =X  OOPS!!! There shouldn't be anything following 'list',\n"
                            + " did you meant to do something else?");
        }
    }
}
