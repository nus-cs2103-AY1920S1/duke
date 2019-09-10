package duke.command;

import duke.task.Task;
import duke.handler.Storage;
import duke.ui.Ui;

import java.io.IOException;
import java.util.ArrayList;

public class ByeCommand extends Command {
    @Override
    public void execute(ArrayList<Task> tasks, Ui ui, Storage storage) throws IOException {
        response = "Bye! Hope to see you again soon.";
        storage.save(tasks);
    }
}
