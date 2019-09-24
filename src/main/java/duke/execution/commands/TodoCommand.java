package duke.execution.commands;

import duke.exception.DukeException;
import duke.execution.Storage;
import duke.execution.TaskList;
import duke.execution.UI;
import duke.models.Task;
import duke.models.ToDo;

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
     * @throws DukeException is thrown if the description is empty.
     */
    @Override
    public void execute(TaskList tasklist, UI ui, Storage storage) throws DukeException {

        super.execute(tasklist, ui, storage);
        checkValidity();

        int intPriority = this.descriptionOfTask.indexOf('*');

        Task newTodo;
        if (intPriority >= 0) {
            String newDescription = this.descriptionOfTask.substring(0, intPriority)
                    + this.descriptionOfTask.substring(intPriority + 1);
            newTodo = new ToDo(newDescription);
            newTodo.markAsPriority();
            tasklist.addPriorityTask(newTodo);
        } else {
            newTodo = new ToDo(this.descriptionOfTask);
            tasklist.addTask(newTodo);
        }
        ui.displayAddingOfTask(newTodo, tasklist.getSize());
        storage.saveToDataFile(tasklist);

    }

    /**
     * Handles the error and checks if it is valid for duke.execution.
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
