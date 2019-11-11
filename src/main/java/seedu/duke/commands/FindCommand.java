package seedu.duke.commands;

import seedu.duke.commands.exceptions.CommandExecutionException;
import seedu.duke.tasks.Task;
import seedu.duke.tasks.TaskManager;

import java.util.List;

/**
 * Shows list of tasks that match the keyword of a 'find' command.
 */
public class FindCommand extends Command {
    private String keyword;

    FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Finds all the tasks that contain a certain keyword in its description.
     *
     * @param taskManager A task manager that handles operations to the list of tasks.
     * @return A response that informs the user about all of the tasks that contain the specified keyword.
     * @throws CommandExecutionException An error that occurs during command execution
     *                                   (e.g. due to input/output failure when updating database file).
     */
    @Override
    public CommandResult execute(TaskManager taskManager) throws CommandExecutionException {
        try {
            List<Task> foundList = taskManager.findTasks(keyword);

            if (foundList.isEmpty()) {
                return new CommandResult("There is no such task in your list");
            }

            StringBuilder feedback = new StringBuilder();
            feedback.append("Here are the matching tasks in your list:\n");
            for (int i = 0; i < foundList.size(); ++i) {
                feedback.append(String.format("%d. %s\n", i + 1, foundList.get(i)));
            }

            return new CommandResult(feedback.toString());
        } catch (Exception e) {
            throw new CommandExecutionException("Unknown error occurred while executing 'find' command");
        }
    }
}
