package duke.command;

import duke.tasklist.TaskList;
import duke.storage.Storage;
import duke.ui.UI;
import duke.exception.DukeException;

public class ErrorCommand extends Command {
    private UI errorPrinter;
    public ErrorCommand(String message) {
        super(message);
    }
    @Override
    public void execute(TaskList listOfTasks, Storage storage, UI ui) throws Exception {
        throw new DukeException(getMessage());
    }
}
