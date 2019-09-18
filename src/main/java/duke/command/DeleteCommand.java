package duke.command;

import duke.exception.DukeException;
import duke.task.Task;
import duke.handler.Storage;
import duke.ui.Ui;

import java.io.IOException;
import java.util.ArrayList;

public class DeleteCommand extends Command {

    protected int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(ArrayList<Task> tasks, Ui ui, Storage storage) throws IOException {
        assert index > 0 && index <= tasks.size() : "The task index does not exist.";
        Task deletedTask = tasks.remove(index - 1);
        response = "Noted. I've deleted the following task:\n    " + deletedTask + "\nNow you have " + tasks.size()
                + " task(s) in the " + "list.";
        storage.save(tasks);
    }
}
