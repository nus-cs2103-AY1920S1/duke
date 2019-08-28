package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public interface Command {
    public void execute(TaskList tasks, Ui ui, Storage storage);
}