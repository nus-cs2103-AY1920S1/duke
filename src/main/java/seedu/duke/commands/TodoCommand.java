package seedu.duke.commands;

import seedu.duke.exceptions.DukeException;
import seedu.duke.tasks.Todo;
import seedu.duke.util.Storage;
import seedu.duke.util.TaskList;
import seedu.duke.util.UI;

import java.io.IOException;

public class TodoCommand extends Command {

    private Todo todoToAdd;

    public TodoCommand(String description) {
        todoToAdd = new Todo(description);
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws DukeException {
        tasks.addToList(todoToAdd);
        ui.operateTask(todoToAdd, tasks, true);
        try {
            storage.writeToFile(tasks);
        } catch (IOException ex) {
            throw new DukeException("☹ OOPS!!! I cannot read your file! :(");
        }
    }
}
