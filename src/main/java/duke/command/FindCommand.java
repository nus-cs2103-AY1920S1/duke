package duke.command;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;
import duke.util.Ui;

import java.util.ArrayList;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    public void execute(TaskList taskList, Ui ui) {
        ArrayList<Task> filteredList = new ArrayList<Task>();
        for (Task task : taskList) {
            if (task.getDescription().contains(this.keyword)) {
                filteredList.add(task);
            }
        }
        TaskList.printExternalList(filteredList, ui, "Here are the matching tasks in your list:");
    }

    public boolean isExit() {
        return false;
    }
}
