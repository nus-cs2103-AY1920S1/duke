package duke.command;

import duke.component.DukeDatabase;
import duke.component.TaskList;
import duke.component.UI;
import duke.exception.DukeException;

public abstract class Command {
    protected TaskList taskList;
    protected UI ui;
    protected DukeDatabase database;
    protected String input;
    protected boolean isExit;

    public Command(String i) {
        input = i;
    }

    protected void initialise(TaskList t, UI u, DukeDatabase d) {
        taskList = t;
        ui = u;
        database = d;
    }

    public abstract void execute(TaskList t, UI u, DukeDatabase d) throws DukeException;

    public boolean isExit() {
        return isExit;
    }
}
