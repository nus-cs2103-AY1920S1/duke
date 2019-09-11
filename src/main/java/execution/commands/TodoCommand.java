package execution.commands;

import exception.DukeException;
import execution.Storage;
import execution.TaskList;
import execution.UI;
import models.Task;
import models.ToDo;

public class TodoCommand extends Command {
    public TodoCommand(String todoTask) {
        super(todoTask);
    }

    @Override
    public void execute(TaskList tasklist, UI ui, Storage storage) throws DukeException {
        super.execute(tasklist, ui, storage);
        checkValidity();

        Task newTodo = new ToDo(this.descriptionOfTask);
        tasklist.addTask(newTodo);
        ui.displayAddingOfTask(newTodo, tasklist.getSize());
        storage.saveToDataFile(tasklist);

    }

    @Override
    public void checkValidity() throws DukeException {
        if(this.descriptionOfTask.isEmpty()) {
            throw new DukeException(" ☹ OOPS!!! The description of a todo cannot be empty.");
        }
    }
}
