package duke.command;

import duke.component.DukeDatabase;
import duke.component.TaskList;
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
     */
    public String execute(DukeDatabase database, TaskList tasksList) {
        Platform.exit();
        return "";
    }
}
