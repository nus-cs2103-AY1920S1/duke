package duke.command;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public class ListCommand extends Command {
    public void execute(TaskList t, Ui ui, Storage storage) {
        ui.printList(t);
    }
}
