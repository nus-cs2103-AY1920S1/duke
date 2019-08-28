package duke.command.list;

import duke.command.Command;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.ArrayList;

public class FindCommand extends Command {

    /** Search keyword. */
    private String keyword;

    /**
     * Constructs the FindCommand object.
     * @param keyword Search keyword
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    /**
     * Lists the Search Results based on the keyword
     * @param tasks The current TaskList object
     * @param ui The current Ui object
     * @param storage The current Storage object
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> taskList = tasks.getTasks();
        ArrayList<Integer> searchResult = new ArrayList<Integer>();
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).toString().toLowerCase().contains(keyword.toLowerCase())) {
                searchResult.add(i);
            }
        }
        ui.listSearchResults(taskList, searchResult);
    }

    @Override
    /**
     * Returns whether this is an exiting command.
     * @return Whether this command exits the application
     */
    public boolean isExit() {
        return false;
    }

}
