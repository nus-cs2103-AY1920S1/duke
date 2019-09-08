package duke.command;

import duke.exception.DukeException;
import duke.parser.DataParser;
import duke.parser.DateParser;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a Command that edits the task date.
 */
public class EditTaskDateCommand extends Command {
    /**
     * Constructs a new Command where it does not terminate the Duke Application.
     */
    public EditTaskDateCommand() {
        super(false);
    }

    /**
     * Executes the specific command based on the type of the command.
     * In this case, it edits the task date.
     * @param taskList The List of tasks involved.
     * @param ui The Interface which deals with user input and interaction.
     * @param storage The storage to load and save task data into the output file.
     * @param dataParser Parses user and task data.
     * @param dateParser Parser date data.
     * @throws DukeException If there is a problem with data processing, loading or saving.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage,
                        DataParser dataParser, DateParser dateParser) throws DukeException {
        String[] newTaskData = dataParser.parseEditTaskNameData();
        Task task = taskList.editSpecificTaskDate(newTaskData);
        ui.showEditedTask(task);
        storage.save();
    }
}
