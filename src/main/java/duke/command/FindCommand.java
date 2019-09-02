package duke.command;

import duke.exception.DukeIllegalArgumentException;
import duke.module.Storage;
import duke.module.TaskList;
import duke.module.Ui;
import duke.task.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the "find" command supported by Duke.
 */
public class FindCommand extends Command {

    /** {@value DUKE_FOUND_TASKS} */
    private static final String DUKE_FOUND_TASKS = "Here are the matching tasks in your list:";
    /** {@value DUKE_NO_FOUND_TASKS} */
    private static final String DUKE_NO_FOUND_TASKS = "There were no matching tasks for the keyword: ";

    /** {@value ERROR_MISSING_KEYWORD} */
    private static final String ERROR_MISSING_KEYWORD = "☹ OOPS!!! Please include a phrase to search for.";

    /** Sequence of characters to match against the descriptions of <code>Task</code>s. */
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Finds a <code>Task</code> in the <code>TaskList</code> with a description that contains {@link #keyword}.
     *
     * @param taskList List of tasks to manage.
     * @param ui UI to show result to user.
     * @param storage Storage to save any changes if necessary.
     * @throws DukeIllegalArgumentException When the keyword is missing.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeIllegalArgumentException {
        if (keyword.isEmpty()) {
            throw new DukeIllegalArgumentException(ERROR_MISSING_KEYWORD);
        }
        List<Task> foundTasks = taskList.findAllTasksContaining(this.keyword);
        ui.printToUser(listTasks(foundTasks));
    }

    /**
     * List all <code>Task</code>s in the given taskList.
     * Implemented to aid in displaying all tasks found containing the {@link #keyword}.
     *
     * @param taskList List of <code>Task</code>s to display to user.
     * @return A String array of lines to display to user.
     */
    private String[] listTasks(List<Task> taskList) {
        if (taskList.size() == 0) {
            return new String[] { DUKE_NO_FOUND_TASKS, String.format("  \"%s\"", this.keyword) };
        }

        List<String> tasks = new ArrayList<>();
        tasks.add(DUKE_FOUND_TASKS);
        for (int i = 0; i < taskList.size(); i++) {
            tasks.add(String.format("  %d.%s",
                    i + 1,
                    taskList.get(i).getStatus()));
        }
        return tasks.toArray(new String[0]);
    }

    /**
     * Returns false.
     *
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }

}
