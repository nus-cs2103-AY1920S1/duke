package duke.commands;

import duke.DukeException;
import duke.tasks.Todo;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.Storage;
import duke.Ui;

public class TodoCommand extends Command {
    private String description;

    public TodoCommand(String description) {
        this.description = description;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task todo = new Todo(this.description);
        taskList.addTask(todo);
        ui.showAddTask(todo, taskList);
        storage.saveData(taskList);
    }
}
