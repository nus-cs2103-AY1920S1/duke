package duke.command;

import duke.DukeException;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

import java.util.List;

public class FindCommand extends Command {
    protected String description;

    public FindCommand(String description) {
        this.description = description;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) throws DukeException {
        List<Task> listTasks = tasks.getTasks();
        ui.showFilteredTasks(listTasks, this.description);
    }
}
