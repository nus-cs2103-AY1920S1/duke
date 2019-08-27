package duke.commands;

import duke.DukeException;
import duke.tasks.Todo;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.Storage;
import duke.ui.Ui;

public class TodoCommand extends Command {
    private String description;

    /**
     * Create a todo command instance.
     * @param description information about the todo task
     */
    public TodoCommand(String description) {
        this.description = description;
    }

    /**
     * Create a todo task.
     * @param taskList task list of the Duke project
     * @param ui an instance of the Ui class
     * @param storage an instance of the storage class
     * @throws DukeException if errors occur when executing the command
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task todo = new Todo(this.description);
        taskList.addTask(todo);
        ui.showAddTask(todo, taskList);
        storage.saveData(taskList);
    }
}
