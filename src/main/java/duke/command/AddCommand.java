package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public abstract class AddCommand extends Command {
    protected String description;

    public AddCommand(final String description) {
        this.description = description;
    }

    @Override
    protected void check(final TaskList tasks) throws DukeException {
        if (this.description.isBlank()) {
            throw new DukeException("The description of a task cannot be empty");
        }
    }

    protected void addTask(final Task task, TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (tasks.addTask(task)) {
            storage.writeTasks(tasks);
            ui.showMessage("Got it. I've added this task:");
            ui.showMessage("  " + task.toString());
            ui.showMessage("Now you have " + tasks.size() + " tasks in the list");
        } else {
            throw new DukeException("Failed to add task");
        }
    }
}
