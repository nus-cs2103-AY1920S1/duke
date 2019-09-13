package duke.command;

import duke.exception.DukeException;
import duke.parser.DataParser;
import duke.parser.DateParser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a Command to add a ToDo Task to the list of tasks.
 */
public class AddToDoTaskCommand extends Command {

    /**
     * Constructs a new Command where it does not terminate the Duke Application.
     */
    public AddToDoTaskCommand() {
        super(false);
    }

    /**
     * Executes the specific command based on the type of the command.
     * In this case, it adds a new ToDo Task to the TaskList, if there are less than 101 tasks.
     * @param taskList The List of tasks involved.
     * @param ui The Interface which deals with user input and interaction.
     * @param storage The storage to load and save task data into the output file.
     * @param dataParser Parses user and task data.
     * @param dateParser Parser date data.
     * @throws DukeException If there is a problem with data processing, loading or saving.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage,
                        DataParser dataParser, DateParser dateParser) throws DukeException {
        assert (!TaskList.hasHitTaskLimit()) : "OOPS!!! You can only have up to 100 tasks!";
        String[] toDoData = dataParser.parseToDoData();
        int taskIndex = taskList.addTodoTask(toDoData);
        ui.showAddedTask(TaskList.getTask(taskIndex));
        storage.save();
    }

    /**
     * Returns a String representation for user guidance.
     * @return a String representation for user guidance.
     */
    public String toString() {
        String helper = "Adds a todo task: \n"
            + "Usgae: todo (taskname)";
        return helper;
    }

}
