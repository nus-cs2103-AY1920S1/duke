package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
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
    public ListCommand(String[] commandArgs) {
        super(commandArgs);
        commandType = Commands.list;
    }

    /**
     * Displays the tasks in the task list.
     *
     * @param tasks TaskList of tasks to use.
     * @param ui Ui to use for displaying command output.
     * @param storage Storage for WritableCommands to execute write-to-disk operations.
     */
    @Override
    public void run(TaskList tasks, Ui ui, Storage storage) {
        ui.printMsgLine(" Here are the tasks in your list:");

        int taskIndex = 1;
        for (Task task : tasks.getAllTasks()) {
            ui.printMsgLine(String.format(" %d.%s", taskIndex, task.getStatusText()));
            taskIndex++;
        }
    }

    /**
     * Checks that there are no other arguments provided to the command.
     *
     * @param tasks TaskList of tasks to use.
     * @param ui Ui to use for displaying command output.
     * @param storage Storage for WritableCommands to execute write-to-disk operations.
     * @throws DukeInvalidArgumentException If there are other arguments.
     */
    @Override
    void validate(TaskList tasks, Ui ui, Storage storage) throws DukeInvalidArgumentException {
        if (commandArgs.length > 0) {
            throw new DukeInvalidArgumentException(
                    "Encountered extraneous arguments after list command",
                    " \u2639 OOPS!!! There shouldn't be anything following 'list',\n"
                            + " did you meant to do something else?");
        }
    }
}
