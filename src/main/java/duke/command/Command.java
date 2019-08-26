package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public abstract class Command {
    public abstract void execute(Ui ui, Storage storage, TaskList taskList);
    public boolean shouldContinue(){
        return true;
    }
}
