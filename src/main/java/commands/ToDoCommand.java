package commands;

import logic.DukeException;
import logic.DukeList;
import logic.DukeStrings;
import logic.Storage;
import logic.Ui;
import task.Task;
import task.ToDo;

/**
 * Encapsulates command to create a ToDo object to be added to the list of tasks.
 */
public class ToDoCommand extends Command {
    private String args;

    public ToDoCommand(String args) {
        this.args = args;
    }

    /**
     * Overridden Method to execute the ToDo command.
     *
     * @param tasks   list of tasks
     * @param ui      User Interface
     * @param storage File Storage and Management
     * @throws DukeException If command arguments is invalid
     */
    @Override
    public void execute(DukeList tasks, Ui ui, Storage storage) throws DukeException {
        if (args.trim().isEmpty()) {
            throw new DukeException(DukeStrings.TODO_EMPTY);
        }

        Task task = new ToDo(false, args); //args is the description string
        tasks.add(task);
        storage.updateTaskFile(tasks);
    }
}
