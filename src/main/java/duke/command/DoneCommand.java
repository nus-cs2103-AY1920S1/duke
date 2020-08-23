package duke.command;

import java.io.IOException;

import duke.error.DukeException;
import duke.error.InvalidIndexException;
import duke.TaskList;
import duke.task.Task;
import duke.util.Storage;
import duke.util.Ui;

public class DoneCommand implements Command {
    private int index;

    /**
     * Constructor.
     *
     * @param index int
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
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            if (index > tasks.getSize() || index < 1) {
                throw new InvalidIndexException("\u2639 OOPS!!! Trying to modify invalid task");
            }
            Task task = tasks.get(index);
            task.markDone();
            String strToPrint = String.format("%s%s",
                    ui.prepend4("Nice! I've marked this task as done:"),
                    ui.prepend6(task.toString()));
            System.out.printf("%s", strToPrint);
            storage.save(tasks);
            return strToPrint;
        } catch (IOException e) {
            ui.printWriteError();
            return ui.stringifyError(e);
        }
    }
}
