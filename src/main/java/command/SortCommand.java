package command;

import main.Storage;
import main.TaskComparer;
import main.TaskList;
import main.Ui;

import java.io.IOException;
import java.util.Collections;

/**
 * Represents the command to sort the current list from.
 */
public class SortCommand extends Command {

    /**
     * Sorts the file in descending order by date. Todos have the lowest priority and are sorted alphabetically.
     *
     * @param task    The working TaskList object.
     * @param ui      The working Ui object.
     * @param storage The working storage object.
     * @return String stating List is sorted.
     */
    public String execute(TaskList task, Ui ui, Storage storage) throws IOException {
        Collections.sort(task.getList(), new TaskComparer());
        storage.arrayToFile(task.getList());
        return "List sorted";
    }
}
