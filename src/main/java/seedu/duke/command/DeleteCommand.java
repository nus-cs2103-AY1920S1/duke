package seedu.duke.command;

import java.io.IOException;

import seedu.duke.DukeException;
import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.task.Task;
import seedu.duke.task.TaskList;

public class DeleteCommand extends Command {
    int number;

    public DeleteCommand(int number) {
        this.number = number;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            Task task = tasks.remove(number);
            storage.store(tasks);
            return ui.getSuccessMessage("deleting", task);
        } catch (IOException e) {
            return "There has been a problem saving the deletion.";

        }
    }
}
