package duke.command;

import duke.exception.DukeException;
import duke.ui.Storage;
import duke.ui.TaskList;
import duke.ui.Ui;

import java.util.ArrayList;
import java.util.List;

/**
 * The command used to list all the tasks.
 */
public class ListCommand extends Command {
    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        List<String> sList = new ArrayList<>();
        String temp;

        int tasksSize = tasks.size();
        if (tasksSize == 0) {
            return Ui.frontSpace + " You have no tasks in your list.\n";
        } else {
            for (int i = 0; i < tasksSize; i++) {
                if ((i + 1) < 10) {
                    temp = Ui.frontSpace + " " +
                            (i + 1) + ". " + tasks.getTaskList().get(i) + "\n";
                } else {
                    temp = Ui.frontSpace + " " +
                            (i + 1) + "." + tasks.getTaskList().get(i) + "\n";
                }
                sList.add(temp);
            }
            StringBuilder result = new StringBuilder();
            for (String str : sList) {
                result.append(str);
            }
            return String.format(
                    Ui.frontSpace + " Here are the tasks in your list:\n" + "%s\n",
                    result.toString());
        }
    }
}