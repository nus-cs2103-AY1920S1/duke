package duke.command;

import duke.DukeException;
import duke.Parser;
import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

public abstract class Command {
    protected Parser parser = new Parser();
    protected String[] args;

    public Command(String[] args) {
        this.args = args;
    }

    public boolean isExit() {
        return (this instanceof Bye);
    }

    public abstract String getName();

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
}
