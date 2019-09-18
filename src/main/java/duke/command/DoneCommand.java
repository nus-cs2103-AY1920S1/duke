package duke.command;

import duke.task.Task;
import duke.handler.Storage;
import duke.ui.Ui;

import java.util.ArrayList;

public class DoneCommand extends Command {

    protected int index;

    public DoneCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(ArrayList<Task> tasks, Ui ui, Storage storage) {
        assert index > 0 && index <= tasks.size() : "The task index does not exist.";
        Task task = tasks.get(index - 1);
        task.markAsDone();
        response = "Nice! I've marked this task as done:\n    " + task;
    }
}
