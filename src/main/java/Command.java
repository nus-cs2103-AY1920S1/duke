import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

interface Command {
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    public default boolean isExit() {
        return false;
    }
}