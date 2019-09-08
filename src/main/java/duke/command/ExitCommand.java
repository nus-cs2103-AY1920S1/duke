package duke.command;

import duke.component.DukeDatabase;
import duke.component.TaskList;
import duke.exception.DukeException;
import javafx.application.Platform;

/**
 * Encapsulates a command which terminates duke.Duke bot.
 */
public class ExitCommand extends Command {
    /**
     * Executes the exit command accordingly.
     *
     * @param tasksList the tasks list of duke.Duke.
     * @param database the database of duke.Duke.
     * @throws DukeException if the user's input is incorrect.
     */
    public String execute(DukeDatabase database, TaskList tasksList) throws DukeException {
        Platform.exit();
        database.update(tasksList); // Update the database before program completely terminates

        return "";
    }
}
