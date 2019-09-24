package seedu.duke.command;

import seedu.duke.core.DukeException;
import seedu.duke.parser.Parser;
import seedu.duke.statistic.Statistic;
import seedu.duke.storage.Storage;
import seedu.duke.task.Todo;
import seedu.duke.tasklist.TaskList;
import seedu.duke.ui.Ui;

/**
 * Represents the "todo" command.
 */
public class TodoCommand extends Command {

    /**
     * Default constructor.
     */
    public TodoCommand() {

    }

    /**
     * Executes the command.
     *
     * @param fullCommand Full String command entered by the User.
     * @param ui User Interface object.
     * @param tasks TaskList object.
     * @param taskStorage Storage object for tasks.
     * @param stats Statistic object.
     * @param statStorage Storage object for stats.
     * @return String sequence to be printed to the User.
     * @throws DukeException Thrown when invalid input from user.
     */
    public String execute(String fullCommand, Ui ui, TaskList tasks, Storage taskStorage, Statistic stats,
                          Storage statStorage) throws DukeException {

        stats.incrementTotalCommandsExecuted();

        checkForInvalidInput(fullCommand);

        Todo newTodo = newTodo(fullCommand);

        tasks.addTask(newTodo);

        return ui.getTodoSequence(tasks, newTodo);

    }

    /**
     * Creates a new Todo object from User string.
     *
     * @param fullCommand User input string.
     * @return New Todo object.
     */
    public Todo newTodo(String fullCommand) throws DukeException {
        String description = Parser.getTodoDescription(fullCommand);
        Todo newTodo = new Todo(description);
        return newTodo;
    }

    /**
     * Checks for invalid input for a Todo command.
     *
     * @param fullCommand User input string.
     * @throws DukeException Throws error to do command has invalid user input.
     */
    public void checkForInvalidInput(String fullCommand) throws DukeException {
        if (fullCommand.length() < 5) {
            // fullCommand contains only the string "todo".
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }

    }

}
