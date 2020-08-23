package duke.command;

import java.io.IOException;

import duke.error.DukeException;
import duke.error.InvalidIndexException;
import duke.TaskList;
import duke.task.Task;
import duke.util.Storage;
import duke.util.Ui;

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
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            if (index > tasks.getSize() || index < 1) {
                throw new InvalidIndexException("\u2639 OOPS!!! Trying to delete invalid task");
            }
            Task removedTask = tasks.remove(index);
            String strToPrint = String.format("%s%s%s", 
                    ui.prepend4("Noted. I've removed the task:"),
                    ui.prepend6(String.format("%s", removedTask)),
                    ui.prepend4(String.format(
                            "Now you have %d tasks in the list.", tasks.getSize())));
            System.out.printf("%s", strToPrint);
            storage.save(tasks);
            return strToPrint;
        } catch (IOException e) {
            ui.printWriteError();
            return ui.stringifyError(e);
        }
    }
}
