package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.util.Storage;
import duke.util.Ui;

public interface Command {
    void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
    // implementation varies for each subclass of Command

    default boolean isExit() {
        return false;
    }
}
