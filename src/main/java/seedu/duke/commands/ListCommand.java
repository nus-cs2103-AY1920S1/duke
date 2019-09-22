package seedu.duke.commands;

import seedu.duke.storage.Storage;
import seedu.duke.task.TaskList;
import seedu.duke.ui.UI;

/**
 * This class represents the "list" command from user. It prints the list of tasks in the task list.
 */
public class ListCommand extends Command {

    @Override
    public String execute(TaskList tasks, UI ui, Storage storage) {
        String reply = "Here are the tasks in your list:\n\t";
        for (int i = 0; i < tasks.size(); i++) {
            reply += (i + 1) + "." + tasks.get(i);
            if (i != tasks.size() - 1) {
                reply += "\n\t";
            }
        }
        return reply;
    }
}
