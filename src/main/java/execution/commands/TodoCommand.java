package execution.commands;

import exception.DukeException;
import execution.Storage;
import execution.TaskList;
import execution.UI;
import models.Task;
import models.ToDo;

/**
 * Represents the characteristics of a TodoCommand object.
 */
public class TodoCommand extends Command {

    /**
     * Constructs a TodoCommand object with the description of the task.
     *
     * @param todoTask description of toDo task.
     */
    public TodoCommand(String todoTask) {

        super(todoTask);

    }

    /**
     * Execution of a TodoCommand object. Here, a new ToDo object would be created and added to a list.
     *
     * @param tasklist arraylist of tasks to add the new todo object to when it is created.
     * @param ui to set a response from duke.
     * @param storage to store any changes in the storage.
     * @throws DukeException
     */
    @Override
    public void execute(TaskList tasklist, UI ui, Storage storage) throws DukeException {

        super.execute(tasklist, ui, storage);
        checkValidity();

        Task newTodo = new ToDo(this.descriptionOfTask);
        tasklist.addTask(newTodo);
        ui.displayAddingOfTask(newTodo, tasklist.getSize());
        storage.saveToDataFile(tasklist);

    }

    /**
     * Handles the error and checks if it is valid for execution.
     *
     * @throws DukeException is thrown when the description of the toDo is empty.
     */
    @Override
    public void checkValidity() throws DukeException {

        if (this.descriptionOfTask.isEmpty()) {
            throw new DukeException(" ☹ OOPS!!! The description of a todo cannot be empty.");
        }

    }
}
