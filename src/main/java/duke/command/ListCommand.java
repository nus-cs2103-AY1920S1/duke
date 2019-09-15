package duke.command;

import duke.io.Storage;
import duke.io.Ui;
import duke.place.Place;
import duke.task.TaskList;

/**
 * Represents a list command, execution lists all tasks.
 */
public class ListCommand extends Command {
    private String input;

    public ListCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (input.equals("places")) {
            ui.list(Place.getList());
        } else {
            ui.list(taskList);
        }
    }
}
