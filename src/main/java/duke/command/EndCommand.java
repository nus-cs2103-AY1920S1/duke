package duke.command;

import duke.exception.DukeException;
import duke.parser.DataParser;
import duke.parser.DateParser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a Command to end the reading of user input and data.
 */
public class EndCommand extends Command {

    /**
     * Constructs a new Command where it does terminate the Duke Application.
     */
    public EndCommand() {
        super(true);
    }

    /**
     * Executes the specific command based on the type of the command.
     * In this case, what it does is to signal the end of input processing.
     * @param taskList The List of tasks involved.
     * @param ui The Interface which deals with user input and interaction.
     * @param storage The storage to load and save task data into the output file.
     * @param dataParser Parses user and task data.
     * @param dateParser Parser date data.
     * @throws DukeException If there is a problem with data processing, loading or saving.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage,
                        DataParser dataParser, DateParser dateParser) throws DukeException {
        ui.sendFarewell();
    }
}
