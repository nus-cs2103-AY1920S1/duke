package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public interface Command {

    void execute(TaskList task, Ui ui, Storage storage);

    boolean isExit();
}
