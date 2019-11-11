package seedu.duke.commands;

import seedu.duke.commands.exceptions.CommandExecutionException;
import seedu.duke.commands.exceptions.InvalidCommandException;
import seedu.duke.tasks.Task;
import seedu.duke.tasks.TaskManager;
import seedu.duke.tasks.comparators.CreationTimeComparator;
import seedu.duke.tasks.comparators.UpdateTimeComparator;

import java.util.Collections;
import java.util.List;

/**
 * Shows the list of tasks to the user.
 */
public class ListCommand extends Command {
    private static final String defaultOrder = "createdAt";
    private String order;

    /**
     * Convenient constructor for <code>ListCommand</code>. By default, if there is no order specified, the
     * tasks will be ordered by creation time.
     */
    ListCommand() {
        order = ListCommand.defaultOrder;
    }

    ListCommand(String order) {
        this.order = order;
    }

    /**
     * Lists all the tasks that are currently stored by the Duke chat bot.
     *
     * @param taskManager A task manager that handles operations to the list of tasks.
     * @return A response that informs the user about all of the tasks that are currently stored by Duke chat bot.
     * @throws CommandExecutionException An error that occurs during command execution
     *                                   (e.g. due to input/output failure when updating database file).
     */
    @Override
    public CommandResult execute(TaskManager taskManager) throws CommandExecutionException, InvalidCommandException {
        try {
            List<Task> tasks = taskManager.getTasks();

            if (tasks.isEmpty()) {
                return new CommandResult("There is no task in your list");
            }

            sortTasks(tasks);
            StringBuilder feedback = new StringBuilder();
            feedback.append("Here are the tasks in your list:\n");
            for (int i = 0; i < tasks.size(); ++i) {
                feedback.append(String.format("%d. %s\n", i + 1, tasks.get(i)));
            }

            return new CommandResult(feedback.toString());
        } catch (InvalidCommandException e) {
            throw e;
        } catch (Exception e) {
            throw new CommandExecutionException("Unknown error occurred while executing 'list' command");
        }
    }

    private void sortTasks(List<Task> tasks) throws InvalidCommandException {
        switch (order) {
        case "createdAt":
            Collections.sort(tasks, new CreationTimeComparator());
            break;
        case "updatedAt":
            Collections.sort(tasks, new UpdateTimeComparator());
            break;
        default:
            throw new InvalidCommandException("Invalid order type for 'list' command: " + order);
        }
    }
}
