package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.MainWindow;

/**
 * Abstraction of find command that extends the generic command class.
 */
class FindCommand extends Command {
    /** Search pattern of this find command. */
    private String searchPattern;

    /**
     * Private constructor of find command to set up command arguments.
     *
     * @param commandArgs Array of string arguments for the command.
     */
    private FindCommand(String[] commandArgs) {
        super(commandArgs);
        commandType = Commands.FIND;
    }

    /**
     * Package private constructor to initialise search pattern.
     * Also initialises command arguments for extensibility.
     *
     * @param commandString Original input command string.
     * @param commandArgs Array of string arguments for the command.
     */
    FindCommand(String commandString, String[] commandArgs) {
        this(commandArgs);
        searchPattern = commandString.replaceFirst("find\\s?", "");
    }

    /**
     * Displays all tasks matching the search pattern in the task list.
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
            if (task.getDescription().contains(searchPattern)) {
                displayMessage.append(String.format("%d.%s\n", taskIndex, task.getStatusText()));
                taskIndex++;
            }
        }
        ui.showMessage(displayMessage.toString());
    }

    /**
     * There is no validation logic for the find command.
     * A search for an empty string is still a valid search.
     *
     * @param tasks TaskList of tasks to use.
     * @param ui MainWindow to use for displaying command output.
     * @param storage Storage for WritableCommands to execute write-to-disk operations.
     */
    @Override
    void validate(TaskList tasks, MainWindow ui, Storage storage) {
    }
}
