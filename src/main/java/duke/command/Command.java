package duke.command;

import duke.DukeException;
import duke.TaskList;

public abstract class Command {
    protected String[] parts;

    public Command(String[] parts) {
        this.parts = parts;
    }

    public abstract String execute(TaskList task) throws DukeException;
}
