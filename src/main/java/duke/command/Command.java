package duke.command;

import duke.ui.Ui;

import duke.task.TaskList;

import duke.storage.Storage;

public abstract class Command {

    public abstract void execute(TaskList taskList, Ui ui, Storage storage);

    public abstract boolean isExit();

}
