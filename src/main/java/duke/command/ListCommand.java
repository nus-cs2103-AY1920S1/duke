package duke.command;

import duke.io.Ui;
import duke.io.Storage;

import duke.task.TaskList;

public class ListCommand extends Command {
    public ListCommand(String command) {
        super(command);
    }

    /**
     * Returns a String representing the TaskList t.
     *
     * @param t the TaskList object
     * @param ui the Ui object
     * @param storage the Storage object
     * @return a String representing the TaskList t
     */
    public String execute(TaskList t, Ui ui, Storage storage) {
        return ui.asDukeMessage(
            new String[]{"Here are the tasks in your list:"},
            new String[]{},
            t.asFormattedList().toArray(new String[t.size()])
        );
    }
}
