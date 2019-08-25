package duke.command;

import duke.task.TaskList;
import duke.exception.DukeException;
import duke.exception.TooManyTasksException;
import duke.parser.DataParser;
import duke.parser.DateParser;
import duke.ui.Ui;
import duke.storage.Storage;

/**
 * Represents a Command to add a ToDo Task to the list of tasks.
 */
public class AddEventTaskCommand extends Command {

    /**
     * Constructs a new Command where it does not terminate the Duke Application.
     */
    public AddEventTaskCommand() {
        super(false);
    }

    /**
     * Executes the specific command based on the type of the command.
     * In this case, it adds a new Event Task to the TaskList, if there are less than 101 tasks.
     * @param taskList The List of tasks involved.
     * @param ui The Interface which deals with user input and interaction.
     * @param storage The storage to load and save task data into the output file.
     * @param dataParser Parses user and task data.
     * @param dateParser Parser date data.
     * @throws DukeException If there is a problem with data processing, loading or saving.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage,
                        DataParser dataParser, DateParser dateParser) throws DukeException {
        if (TaskList.getNumberOfTasks() >= 100) {
            throw new TooManyTasksException();
        }
        String[] eventData = dataParser.parseEventDate();
        dateParser.readInput(eventData[1]);
        String dateOutput = dateParser.convertDateToString();
        String nameOutput = eventData[0];
        int taskIndex = taskList.addEventTask(nameOutput, dateOutput);
        ui.showAddedTask(TaskList.getTask(taskIndex));
        storage.save();
    }

}
