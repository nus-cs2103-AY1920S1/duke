package duke.command;

import duke.util.exception.DukeException;
import duke.util.exception.ExceptionType;
import duke.task.TaskList;
import duke.util.Storage;
import duke.util.Ui;

public class ListCommand implements Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.isEmpty()) {
            ui.showError(new DukeException(ExceptionType.TASK_LIST_EMPTY));
        }
        tasks.printList();
    }
}
