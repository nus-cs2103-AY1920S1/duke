package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Task;
import duke.task.Todo;

/**
 * Represents a todo command.
 * An <code>AddTodoCommand</code> object corresponds to a command to add a <code>Todo</code> object
 * to a <code>TaskList</code>.
 */
public class AddTodoCommand extends Command {
    protected String details;

    /**
     * Constructor for <code>AddTodoCommand</code>.
     * @param details Details required to create a <code>Todo</code> object, which includes a task description.
     */
    public AddTodoCommand(String details) {
        super();
        this.details = details;
    }

    /**
     * Creates a new <code>Todo</code> object and adds it into input <code>TaskList</code>.
     * Calls the method in the <code>Ui</code> object to output a message.
     * Calls the method in the <code>Storage</code> object to write all <code>Task</code> objects in the <code>TaskList</code> to the hard disk.
     * @param tasks Instance of <code>TaskList</code> which stores <code>Task</code> objects.
     * @param ui Instance of <code>Ui</code> which handles user input and outputs.
     * @param storage Instance of <code>Storage</code> which stores and loads information to and from the hard disk.
     * @throws DukeException If insufficient or incorrect details are provided.
     */

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (details.trim().length() == 0) {
            throw new DukeException("\u2639 OOPS!!! The description of a todo cannot be empty.");
        }
        Task todo = new Todo(details.trim());
        tasks.addTask(todo);
        int numberOfTasks = tasks.getListSize();
        ui.printAddedMessage(todo, numberOfTasks);
        try {
            storage.writeToHardDisk(tasks);
        } catch (DukeException exception) {
            ui.printException(exception);
        }
    }

    /**
     * Checks if this object is an <code>ExitCommand</code>.
     * @return Whether this command is an exit command.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Returns the input details for the <code>AddTodoCommand</code>.
     * @return Details for command.
     */
    public String getDetails() {
        return details;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (o instanceof AddTodoCommand) {
            AddTodoCommand obj = (AddTodoCommand) o;
            return obj.getDetails().equals(details);
        } else {
            return false;
        }
    }
}
