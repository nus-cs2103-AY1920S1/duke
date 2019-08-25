package duke.command;

import duke.task.TaskList;
import duke.exception.DukeException;
import duke.exception.TooManyTasksException;
import duke.parser.DataParser;
import duke.parser.DateParser;
import duke.ui.Ui;
import duke.storage.Storage;

/**
 * Represents a Command to be executed during the running of the Duke Application.
 */
public class Command {

    public boolean isExit;

    /**
     * Constructs a new Command where it does not terminate the Duke Application.
     * @param isExit Represents whether the Application should terminate or not.
     */
    public Command(boolean isExit) {
        this.isExit =  isExit;
    }

    /**
     * Executes the specific command based on the type of the command.
     * @param taskList The List of tasks involved.
     * @param ui The Interface which deals with user input and interaction.
     * @param storage The storage to load and save task data into the output file.
     * @param dataParser Parses user and task data.
     * @param dateParser Parser date data.
     * @throws DukeException If there is a problem with data processing, loading or saving.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage,
                        DataParser dataParser, DateParser dateParser) throws DukeException {

    }

}
