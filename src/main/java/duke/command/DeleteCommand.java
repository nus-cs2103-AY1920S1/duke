package duke.command;

import java.io.IOException;

import duke.error.DukeException;
import duke.error.InvalidIndexException;
import duke.TaskList;
import duke.util.Storage;
import duke.util.Ui;
import duke.task.Task;

public class DeleteCommand implements Command {
    private int index;

    /**
     * Constructor.
     */
    public DeleteCommand(int index) {
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
                throw new InvalidIndexException("\u2639 OOPS!!! Trying to delete invalid task");
            }
            Task removedTask = tasks.remove(index - 1);
            ui.prettyPrint4("Noted. I've removed the task:");
            ui.prettyPrint6(String.format("%s\n", removedTask)); 
            ui.prettyPrint4(String.format("Now you have %d tasks in the list.\n", tasks.getSize())); 
            storage.save(tasks);
        } catch (IOException e) {
            ui.printWriteError();
        }
    }
}
