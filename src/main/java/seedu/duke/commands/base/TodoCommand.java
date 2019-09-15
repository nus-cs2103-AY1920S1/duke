package seedu.duke.commands.base;

import seedu.duke.exceptions.DukeException;
import seedu.duke.tasks.Todo;
import seedu.duke.util.Storage;
import seedu.duke.util.TaskList;
import seedu.duke.util.UI;

import java.io.IOException;

/**
 * Command to add a Todo task to the TaskList class.
 */
public class TodoCommand extends Command {

    private Todo todoToAdd;

    /**
     * Constructor that primes the todo to be added to TaskList.
     *
     * @param description Description of todo.
     */
    public TodoCommand(String description) {
        todoToAdd = new Todo(description);
    }

    /**
     * Adds the todo to the TaskList, inform the user that it has been done and writes the
     * new change to the text file.
     *
     * @param tasks TaskList of tasks to be operated on.
     * @param ui UI to display messages to the user.
     * @param storage Storage to write or read files if applicable.
     * @throws DukeException Throws if storage cannot find the file to write to.
     */
    @Override
    public String execute(TaskList tasks, UI ui, Storage storage) throws DukeException {
        tasks.addToList(todoToAdd);
        try {
            storage.writeToFile(tasks);
        } catch (IOException ex) {
            throw new DukeException("OOPS!!! I cannot read your file! :(");
        }

        return ui.operateTask(todoToAdd, tasks, true);
    }
}
