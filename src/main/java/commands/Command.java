package commands;

import components.Storage;
import components.Ui;
import components.TaskList;

public interface Command {
    public void execute(Ui ui, Storage storage, TaskList taskList);
    public default boolean isExit() {
        return false;
    }
}
