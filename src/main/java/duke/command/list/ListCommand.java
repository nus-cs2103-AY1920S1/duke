package duke.command.list;

import duke.command.Command;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.ArrayList;

public class ListCommand extends Command {

    public void execute(TaskList tasks, Ui ui) {
        ui.listTasks(tasks.getTasks());
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
