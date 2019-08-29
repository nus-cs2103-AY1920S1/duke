package duke.command;

import duke.component.DukeDatabase;
import duke.component.TaskList;
import duke.component.Ui;
import duke.exception.DukeException;

/**
 * Encapsulates a command to be executed by Duke bot.
 */
public abstract class Command {
    protected TaskList taskList;
    protected Ui ui;
    protected DukeDatabase database;
    protected String input;

    /**
     * Constructs a Command object.
     *
     * @param i user's input.
     */
    public Command(String i) {
        input = i;
    }

    /**
     * Initialises the dependencies of the Command object.
     *
     * @param t the taskList of Duke.
     * @param u the ui of Duke.
     * @param d the database of Duke.
     */
    protected void initialise(TaskList t, Ui u, DukeDatabase d) {
        taskList = t;
        ui = u;
        database = d;
    }

    /**
     * Executes the command that this object encapsulates.
     *
     * @param t the taskList of Duke.
     * @param u the ui of Duke.
     * @param d the database of Duke.
     * @throws DukeException depending on the actual Command type of the object.
     */
    public abstract void execute(TaskList t, Ui u, DukeDatabase d) throws DukeException;

    /**
     * Checks if the command is an ExitCommand.
     *
     * @return true if the command is an ExitCommand and false otherwise.
     */
    public boolean isExit() {
        return this instanceof ExitCommand;
    }
}
