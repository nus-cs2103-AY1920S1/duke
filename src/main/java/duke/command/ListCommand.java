package duke.command;

import duke.task.TaskList;
import duke.util.Storage;
import duke.util.Ui;

public class ListCommand implements Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.printList();
    }
}
