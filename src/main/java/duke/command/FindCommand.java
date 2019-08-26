package duke.command;

import duke.exception.DukeException;
import duke.task.Task;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

import java.util.ArrayList;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList t, Ui ui, Storage storage) {
        ArrayList<Task> foundTaskList = new ArrayList<>();
        for (Task task : t.list) {
            String description = task.getDescription().toLowerCase();
            if (description.contains(keyword)) {
                foundTaskList.add(task);
            }
        }
        ui.showFoundTask(foundTaskList);
    }
}
