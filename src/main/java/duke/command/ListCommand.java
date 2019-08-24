package duke.command;

import duke.TaskList;
import duke.Ui;

public class ListCommand extends Command {
    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) {
        ui.showAllTasks(tasks.getTasks());
    }
}
