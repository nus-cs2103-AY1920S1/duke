package seedu.duke.commands;

import seedu.duke.task.TaskList;
import seedu.duke.ui.UI;

/** Prints out all the tasks in the Task list.
 * @param taskList The list that contains/may not contain tasks.
 * @param ui User input that handles the printing.
 */
public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, UI ui, String fw) {
        String reply = "Here are the tasks in your list:\n\t ";
        for (int i = 0; i < tasks.size(); i++) {
            reply += (i + 1) + "." + tasks.get(i);
            if (i != tasks.size() - 1) {
                reply += "\n\t ";
            }
        }
        ui.printReply(reply);
    }
}
