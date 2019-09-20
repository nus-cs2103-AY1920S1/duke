package seedu.duke.commands;

import seedu.duke.storage.Storage;
import seedu.duke.task.Task;
import seedu.duke.task.TaskList;
import seedu.duke.ui.UI;

/**
 * This class represents the "find" command by user and prints out all the tasks in the Task list containing the
 * keyword input by user.
 */
public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList tasks, UI ui, Storage storagae) {
        TaskList matchingTasks = new TaskList();
        String reply = "Here are the matching tasks in your list:\n\t ";
        for (Task task: tasks) {
            if (task.getDescription().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        for (int i = 1; i <= matchingTasks.size(); i++) {
            reply += i + "." + matchingTasks.get(i - 1);
            if (i < matchingTasks.size()) {
                reply += "\n\t ";
            }
        }
        return reply;
    }
}
