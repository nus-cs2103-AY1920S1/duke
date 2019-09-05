package duke.command;

import duke.DukeException;
import duke.common.Message;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Finds all tasks in the task list that matches given keyword.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Creates a command to find tasks that matches this keyword.
     *
     * @param keyword keyword that is used to find tasks.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes finding of matching tasks.
     *
     * @param taskList list of tasks.
     * @param storage local storage of data.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws DukeException {
        StringBuilder output = new StringBuilder(String.format(Message.MESSAGE_SHOW_TASK_LIST, " matching"));
        for (String taskName : taskList.getTaskNamesIfMatch(this.keyword)) {
            output.append("\n");
            output.append(taskName);
        }
        return output.toString();
    }
}
