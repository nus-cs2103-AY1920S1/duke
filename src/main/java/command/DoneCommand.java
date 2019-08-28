package command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

import static java.lang.String.format;

/**
 * Marks a task as done.
 *
 */
public class DoneCommand extends Command {
    private int index;

    /**
     * Marks a task as done.
     *
     * @param input Given user input.
     * @throws DukeException If any error is encountered.
     */
    public DoneCommand(String input) throws DukeException {
        this.index = Integer.parseInt(input);
        this.setExit(false);
    }

    @Override
    public void execute(TaskList tasklist, Ui ui, Storage storage) throws DukeException {
        if (index < 1 || index > tasklist.getTaskSize()) {
            throw new DukeException("Index out of range.");
        }
        tasklist.markDone(index);
        ui.printStatement("Nice! I've marked this task as done:",
                format("  %s", tasklist.getTaskByIndex(index).toString()));
        storage.updateData(tasklist);
    }
}


