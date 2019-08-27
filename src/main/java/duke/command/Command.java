package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public interface Command {
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    public default boolean isExit() {
        return false;
    }
}