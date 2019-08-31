package seedu.duke.commands;

import seedu.duke.exceptions.DukeException;
import seedu.duke.tasks.Task;
import seedu.duke.util.Storage;
import seedu.duke.util.TaskList;
import seedu.duke.util.UI;

import java.util.ArrayList;

public class FindCommand extends Command {

    private String toFind;

    public FindCommand(String toFind) {
        this.toFind = toFind;
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws DukeException {
        ArrayList<Task> temp = new ArrayList<>();
        ArrayList<Task> fullList = tasks.getTaskList();
        for (Task task: fullList) {
            if (task.toString().contains(toFind)) {
                temp.add(task);
            }
        }
        TaskList temp1 = new TaskList(temp);
        ui.showFound(temp1);
    }
}
