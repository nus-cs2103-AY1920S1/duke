package duke.commands;

import duke.tasklist.TaskList;
import duke.ui.UI;
import duke.storage.Storage;

public abstract class Command {

    public abstract void execute(TaskList toDoList, UI ui, Storage storage);

    public abstract boolean isExit();
}