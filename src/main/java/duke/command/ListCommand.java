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
        assert tasks != null;
        assert ui != null;
        assert storage != null;

        List<String> strList = new ArrayList<>();

        int tasksSize = tasks.size();
        if (tasksSize == 0) {
            return Ui.frontSpace + " You have no tasks in your list.\n";
        } else {
            updateList(tasks, strList, tasksSize);
            return printList(strList);
        }
    }

    /**
     * print out the list when list command is called.
     *
     * @param strList The list of tasks need to print out.
     * @return The output of the command in String format.
     */
    private String printList(List<String> strList) {
        StringBuilder result = new StringBuilder();
        for (String str : strList) {
            result.append(str);
        }
        return String.format(
                Ui.frontSpace + " Here are the tasks in your list:\n" + "%s\n",
                result.toString());
    }

    /**
     * store the task in slist.
     *
     * @param tasks     The TaskList need to deal with.
     * @param strList   The list of tasks need to print out.
     * @param tasksSize The size of the tasks need to print out.
     */
    private void updateList(TaskList tasks, List<String> strList, int tasksSize) {
        String temp;
        for (int i = 0; i < tasksSize; i++) {
            if ((i + 1) < 10) {
                temp = Ui.frontSpace + " "
                        + (i + 1) + ". " + tasks.getTaskList().get(i) + "\n";
            } else {
                temp = Ui.frontSpace + " "
                        + (i + 1) + "." + tasks.getTaskList().get(i) + "\n";
            }
            strList.add(temp);
        }
    }
}