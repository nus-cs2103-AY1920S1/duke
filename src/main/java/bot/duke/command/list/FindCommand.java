package bot.duke.command.list;

import java.util.ArrayList;

import bot.duke.command.Command;
import bot.duke.storage.Storage;
import bot.duke.task.Task;
import bot.duke.task.TaskList;
import bot.duke.ui.Ui;

public class FindCommand extends Command {

    /** Search keyword. */
    private String keyword;

    /**
     * Constructs the FindCommand object.
     *
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
            Task currTask = taskList.get(i);
            assert currTask != null;
            boolean hasKeyword =  currTask.toString().toLowerCase().contains(keyword.toLowerCase());
            boolean hasDateLike = currTask.toDelimitedString()
                    .replaceAll("\\D+","")
                    .contains(keyword.replaceAll("\\D+",""))
                    && !keyword.replaceAll("\\D+","").isEmpty();
            if (hasKeyword | hasDateLike) {
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
