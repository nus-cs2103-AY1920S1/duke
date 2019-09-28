package duke.commands;

import java.util.ArrayList;
import java.util.List;

import duke.model.Model;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Find a task containing the given keyword.
     * @param model model of the Duke project
     * @param ui an instance of the Ui class
     * @param storage an instance of the storage class
     */
    @Override
    public void execute(Model model, Ui ui, Storage storage) {
        TaskList taskList = model.getTaskList();
        List<Task> selectedTasks = new ArrayList<Task>();
        for (int i = 0; i < taskList.getSize(); i++) {
            Task task = taskList.getTask(i);
            if (task.getDescription().toLowerCase().contains(this.keyword)) {
                selectedTasks.add(task);
            }
        }
        if (selectedTasks.size() == 0) {
            ui.showNoMatchingTask();
        } else {
            ui.showMatchingTasks(selectedTasks);
        }
    }
}
