package duke.command;

import duke.main.Storage;
import duke.main.Ui;
import duke.main.TaskList;

public class ListCommand implements Command {
    public ListCommand() {
    }

    public void execute(Storage storage, Ui ui, TaskList tasks) {
        ui.output("Here are the tasks in your list: ");
        for (int i = 0; i < tasks.getTasksSize(); i++) {
            ui.output((i + 1) + ". " + tasks.getTask(i).toString());
        }
    }

    public boolean isRunning() {
        return true;
    }
}
