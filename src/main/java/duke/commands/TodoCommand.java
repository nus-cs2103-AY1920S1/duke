package duke.commands;

import duke.exception.DukeException;
import duke.model.Model;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.Todo;
import duke.ui.Ui;

public class TodoCommand extends Command {
    private String description;

    /**
     * Create a to-do command instance.
     * @param description information about the todo task
     */
    public TodoCommand(String description) {
        this.description = description;
    }

    /**
     * Create a to-do task.
     * @param model model of the Duke project
     * @param ui an instance of the Ui class
     * @param storage an instance of the storage class
     * @throws DukeException if errors occur when executing the command
     */
    @Override
    public void execute(Model model, Ui ui, Storage storage) throws DukeException {
        TaskList taskList = model.getTaskList();
        Task todo = new Todo(this.description);
        taskList.addTask(todo);
        ui.showAddTask(todo, taskList);
        storage.saveData(model);
    }
}
