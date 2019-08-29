package duke.command;

import duke.component.DukeDatabase;
import duke.component.TaskList;
import duke.component.Ui;
import duke.exception.DukeException;

/**
 * Encapsulates a command which terminates Duke bot.
 */
public class ExitCommand extends Command {
    /**
     * Construct an ExitCommand object.
     */
    public ExitCommand() {
        super("");
    }

    /**
     * Executes the exit command accordingly.
     *
     * @param tasksList the tasks list of Duke.
     * @param ui the ui of Duke.
     * @param database the database of Duke.
     * @throws DukeException if the user's input is incorrect.
     */
    public void execute(TaskList tasksList, Ui ui, DukeDatabase database) throws DukeException {
        initialise(tasksList, ui, database);
        exit();
    }

    /**
     * Clean up the essential components of Duke bot and terminates the bot.
     */
    public void exit() {
        ui.echo("Bye. Hope to see you again!");
        database.update(taskList);
    }
}
