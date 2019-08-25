package duke.command;

import duke.io.Ui;
import duke.io.Storage;

import duke.task.TaskList;

public class ListCommand extends Command {
    public ListCommand(String command) {
        super(command);
    }

    /**
     * Prints the TaskList t.
     *
     * @param t the TaskList object
     * @param ui the Ui object
     * @param storage the Storage object
     */
    public void execute(TaskList t, Ui ui, Storage storage) {
        ui.print(
            new String[]{"Here are the tasks in your list:"},
            new String[0],
            t.asFormattedList().toArray(new String[t.size()])
        );
    }
}
