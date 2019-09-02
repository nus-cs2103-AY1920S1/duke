package duke.command;

import duke.DukeException.DukeException;
import duke.task.Deadline;
import duke.task.Task;
import duke.taskHandler.Storage;
import duke.ui.Ui;

import java.util.ArrayList;

public class DeadlineCommand extends Command {
    protected String description;
    protected String by;

    public DeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(ArrayList<Task> tasks, Ui ui, Storage storage) throws DukeException {
        if (description.equals("")) {
            throw new DukeException("The description of a deadline cannot be empty.");
        }
        Task deadlineTask = new Deadline(description, by);
        tasks.add(deadlineTask);
        ui.printAddedTask(deadlineTask, tasks);
    }
}
