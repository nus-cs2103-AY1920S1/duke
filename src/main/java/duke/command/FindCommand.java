package duke.command;

import duke.exception.IllegalDescriptionException;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.ArrayList;
import java.util.List;

/**
 * A class representing a find command.
 */
public class FindCommand extends Command{
    public static final String COMMAND_WORD = "find";
    private String keyword;

    /**
     * Class constructor specifying the keyword.
     * @param keyword the keyword to be searched.
     * @throws IllegalDescriptionException If the keyword is empty.
     */
    public FindCommand(String keyword) throws IllegalDescriptionException {
        if(keyword.isEmpty()) {
            throw new IllegalDescriptionException("The keyword cannot be empty.");
        }
        this.keyword = keyword.toLowerCase();
    }

    /**
     * Executes the command.
     * @param tasks a list task to work on.
     * @param ui an user interface to show messages.
     */
    @Override
    public void execute(TaskList tasks, Ui ui){
        List<Task> taskList = tasks.getTasks();
        List<Task> matchingTasks = new ArrayList<>();
        for(Task task: taskList) {
            if(task.toString().toLowerCase().indexOf(keyword) != -1) {
                matchingTasks.add(task);
            }
        }
        ui.showMatchingTasks(matchingTasks);
    }
}
