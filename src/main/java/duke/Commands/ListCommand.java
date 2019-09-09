package duke.Commands;

import duke.TaskList;
import duke.Ui;
import duke.Storage;

public class ListCommand extends Command {

    public String execute(TaskList currentTaskList, Ui ui, Storage storage) {
        return ui.printList(currentTaskList);
    }
}
