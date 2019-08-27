package duke.commands;

import duke.Storage;
import duke.ui.Ui;
import duke.tasks.Task;
import duke.tasks.TaskList;

import java.util.ArrayList;
import java.util.List;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
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
