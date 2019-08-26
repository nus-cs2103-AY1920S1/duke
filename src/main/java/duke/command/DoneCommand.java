package duke.command;

import java.io.IOException;

import duke.TaskList;
import duke.task.Task;
import duke.util.Storage;
import duke.util.Ui;
import duke.error.DukeException;
import duke.error.InvalidIndexException;

public class DoneCommand implements Command {
    private int index;

    /**
     * Constructor.
     */
    public DoneCommand(int index) {
        this.index = index;
    }

    /**
     * Check if exit.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Execute.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            if (index > tasks.getSize() || index < 1) {
                throw new InvalidIndexException("\u2639 OOPS!!! Trying to modify invalid task");
            }
            Task task = tasks.get(index);
            task.markDone();
            ui.prettyPrint4(String.format("Nice! I've marked this task as done:"));
            ui.prettyPrint6(String.format("%s", task)); 
            storage.save(tasks);
        } catch (IOException e) {
            ui.printWriteError();
        }
    }
}
