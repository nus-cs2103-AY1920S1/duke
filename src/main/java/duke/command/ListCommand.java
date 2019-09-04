package duke.command;

import duke.exception.DukeException;
import duke.task.Task;
import duke.handler.Storage;
import duke.ui.Ui;

import java.io.IOException;
import java.util.ArrayList;

public class ListCommand extends Command {
    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(ArrayList<Task> tasks, Ui ui, Storage storage) throws DukeException, IOException {
        if (tasks.size() == 0) {
            ui.showNoTaskResponse();
        } else {
            ui.showListResponse();
            ui.printTasks(tasks);
        }
    }
}
