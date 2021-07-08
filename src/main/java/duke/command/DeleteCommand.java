package duke.command;

import duke.Duke;
import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.ui.Ui;
import duke.task.Task;

import static duke.ui.ErrorMsg.INVALID_INDEX;

public class DeleteCommand extends IndexBasedCommand {

    public DeleteCommand(String line) throws IndexOutOfBoundsException, NumberFormatException, DukeException {
        super(line);
    }

    @Override
    public void execute(Duke duke, TaskList taskList, Ui ui, Storage storage) throws DukeException {
        try {
            Task t = taskList.remove(index);
            ui.println("Noted. I've removed this task: ");
            ui.println("  " + t.toString());
            ui.println("Now you have " + taskList.size() + " tasks in the list.");
            storage.saveTaskListToFile(taskList);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(INVALID_INDEX);
        }
    }
}
