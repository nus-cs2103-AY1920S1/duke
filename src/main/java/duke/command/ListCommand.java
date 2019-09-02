package duke.command;

import duke.error.DukeException;
import duke.TaskList;
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
     * Execute.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String strToReturn = ui.prepend4("Here are the tasks in your list:");
        System.out.printf("%s", strToReturn);
        int length = tasks.getSize();
        for (int i = 1; i <= length; i++) {
            String stringifiedTask = ui.prepend4(String.format("%d.%s", i, tasks.get(i)));
            System.out.printf("%s", stringifiedTask);
            strToReturn += stringifiedTask;
        }
        return strToReturn;
    }
}
