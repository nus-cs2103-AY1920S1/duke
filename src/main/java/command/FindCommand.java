package command;

import duke.Storage;
import task.Task;
import task.TaskList;
import ui.UserInterface;

import java.util.ArrayList;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }
    public void execute(TaskList tasks, UserInterface ui, Storage storage) {
        ArrayList<Task> taskList = tasks.find(keyword);

        if (taskList.isEmpty()) {
            super.message = "No tasks matched your description.";
        } else {
            super.message = "Here are the matching tasks in your list:\n";
            super.message += tasks.list(taskList);
        }

    }
}
