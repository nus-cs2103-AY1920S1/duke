package duke.command;

import duke.TaskList;
import duke.error.DukeException;
import duke.util.Storage;
import duke.util.Ui;

public class ListCommand implements Command {
    /**
     * Should exit.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the command.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.prettyPrint4("Here are the tasks in your list:");
        int length = tasks.getSize();
        for (int i = 1; i <= length; i++) {
            ui.prettyPrint4(String.format("%d.%s", i, tasks.get(i)));
        }
    }
}
