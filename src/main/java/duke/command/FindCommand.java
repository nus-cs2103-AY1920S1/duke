package duke.command;

import duke.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import static duke.ui.Message.MESSAGE_FIND;
import static duke.ui.Message.MESSAGE_NO_MATCHING_TASKS;
import static duke.ui.Message.concatLines;

/**
 * Finds and lists all tasks in the task list whose description contains the specified keyword.
 * Keyword matching is case sensitive.
 */
public class FindCommand extends Command {
    private String keyWord;

    public FindCommand(String keyWord) {
        this.keyWord = keyWord;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList filteredTasks = tasks.filterByKeyWord(this.keyWord);
        if (filteredTasks.isEmpty()) {
            return MESSAGE_NO_MATCHING_TASKS;
        } else {
            return concatLines(MESSAGE_FIND, filteredTasks.toString());
        }
    }
}
