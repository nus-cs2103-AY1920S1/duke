package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;
import duke.task.Task;

import java.util.ArrayList;

public class FindCommand extends Command {
    private String searchPhrase;
    
    public FindCommand(String searchPhrase) {
        this.searchPhrase = searchPhrase;
    }
    
    /**
     * Searches through the input TaskList for Task objects whose descriptions contain the input search phrase.
     *
     * @param taskList The TaskList object passed from Duke.
     * @param ui       The Ui object passed from Duke.
     * @param storage  The Storage object passed from Duke.
     * @return The response String.
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        ArrayList<Task> findTasksList = new ArrayList<>();
        for (int i = 0; i < taskList.getSize(); i++) {
            if (taskList.getTask(i).getDescription().contains(searchPhrase)) {
                findTasksList.add(taskList.getTask(i));
            }
        }
        if (!findTasksList.isEmpty()) {
            String response = "Here are the matching tasks in your list:";
            for (int i = 0; i < findTasksList.size(); i++) {
                response = response.concat("\n" + (i + 1) + ". " + findTasksList.get(i).toString());
            }
            return response;
        } else {
            return "There are no matching tasks in your list!";
        }
    }
}
