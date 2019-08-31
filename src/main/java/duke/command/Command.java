package duke.command;

import duke.*;
import duke.task.TaskList;

abstract public class Command {
    protected Parser parser = new Parser();

    protected String[] args;
    public Command(String[] args) {
        this.args = args;
    }

    public boolean isExit() {
        return (this instanceof Bye);
    }
    abstract public String getName();
    abstract public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
}
