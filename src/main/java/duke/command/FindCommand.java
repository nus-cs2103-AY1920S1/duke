package duke.command;

import duke.exception.IllegalDescriptionException;
import duke.task.Task;
import duke.task.TaskList;

import java.util.ArrayList;

/**
 * A class representing a find command.
 */
public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";
    private String keyword;

    /**
     * Class constructor specifying the keyword.
     * @param keyword the keyword to be searched.
     * @throws IllegalDescriptionException If the keyword is empty.
     */
    public FindCommand(String keyword) throws IllegalDescriptionException {
        if (keyword.isEmpty()) {
            throw new IllegalDescriptionException("The keyword cannot be empty.");
        }
        this.keyword = keyword.toLowerCase();
    }

    /**
     * Returns the result of executing the command.
     * @param tasks a list task to work on.
     * @return the result of executing the command.
     */
    @Override
    public CommandResult execute(TaskList tasks) {
        ArrayList<Task> taskList = tasks.getTasks();
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task: taskList) {
            if (task.toString().toLowerCase().indexOf(keyword) != -1) {
                matchingTasks.add(task);
            }
        }
        return new CommandResult(CommandType.Find, matchingTasks);
    }
}
