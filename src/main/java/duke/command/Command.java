package duke.command;

import duke.component.DukeDatabase;
import duke.component.TaskList;
import duke.component.Ui;
import duke.exception.DukeException;

public abstract class Command {
    protected TaskList taskList;
    protected Ui ui;
    protected DukeDatabase database;
    protected String input;
    protected boolean isExit;

    public Command(String i) {
        input = i;
    }

    protected void initialise(TaskList t, Ui u, DukeDatabase d) {
        taskList = t;
        ui = u;
        database = d;
    }

    public abstract void execute(TaskList t, Ui u, DukeDatabase d) throws DukeException;

    public boolean isExit() {
        return isExit;
    }
}
