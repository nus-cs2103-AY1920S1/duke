package command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import task.Task;

import static java.lang.String.format;

/**
 * Command to delete a task.
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Command to delete a task.
     *
     * @param input the task number in string.
     * @throws DukeException If the task number is of invalid format.
     */
    public DeleteCommand(String input) throws DukeException {
        try {
            this.index = Integer.parseInt(input);
            this.setExit(false);
        } catch (NumberFormatException e) {
            throw new DukeException("Invalid Number Format.");
        }
    }

    @Override
    public void execute(TaskList tasklist, Ui ui, Storage storage) throws DukeException {
        if (index < 1 || index > tasklist.getTaskSize()) {
            throw new DukeException("Index out of range.");
        }
        Task task = tasklist.removeTaskByIndex(index);
        ui.printStatement("Noted. I've removed this task:",
                format("  %s", task.toString()),
                format("Now you have %d tasks in the list.", tasklist.getTaskSize()));
        storage.updateData(tasklist);
    }
}
