package duke.command;

import duke.Duke;
import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class DoneCommand extends IndexBasedCommand {

    public DoneCommand(String line) throws IndexOutOfBoundsException, NumberFormatException, DukeException {
        super(line);
    }

    @Override
    public void execute(Duke duke, TaskList taskList, Ui ui, Storage storage) throws DukeException {
        try {
            Task t = taskList.get(index);
            t.markAsDone(true);
            ui.println("Nice! I've marked this task as done: ");
            ui.println("  " + t);
            storage.saveTaskListToFile(taskList);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("The index is invalid.");
        }
    }
}
