package duke.command;

import duke.core.Ui;
import duke.core.Storage;
import duke.helper.DukeException;
import duke.task.TaskList;

public abstract class Command {
    protected String inputCommand;
    protected boolean shouldExit;

    public Command(String inputCommand){
        this.inputCommand = inputCommand;
        this.shouldExit = false;
    }

    public boolean shouldExit(){
        return this.shouldExit;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

}
