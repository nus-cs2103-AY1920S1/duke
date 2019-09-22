package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents command to search for a task by searching a keyword.
 */
public class FindCommand extends Command {
    /** Keyword entered by user. */
    protected String userIn;

    /**
     * Initializes a FindCommand object with keyword entered by user.
     * @param userIn keyword entered by user.
     */
    public FindCommand(String userIn) {
        this.userIn = userIn;
    }

    /**
     * Finds tasks that contain the keyword entered by user.
     * @param tasks TaskList representing a list of all tasks.
     * @param storage storage used for saving, loading and storing user data.
     * @return CommandResult result of executing the command.
     */
    public CommandResult execute(TaskList tasks, Storage storage) {
        List<Task> taskWithKey = tasks.getStream()
                .filter(t -> t.hasWord(userIn)).collect(Collectors.toList());
        return new CommandResult(listTasks(taskWithKey));
    }

    private String listTasks(List<Task> tasks) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the matching tasks in your list \n");
        int count = 1;

        for (Task t:tasks) {
            sb.append(count + ": " + t + "\n");
            count++;
        }

        return sb.toString();
    }
}
