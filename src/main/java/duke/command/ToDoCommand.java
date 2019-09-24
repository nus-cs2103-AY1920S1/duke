package duke.command;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.ToDo;
import duke.handler.Storage;
import duke.ui.Ui;

import java.io.IOException;
import java.util.ArrayList;

public class ToDoCommand extends Command {
    protected String description;

    public ToDoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(ArrayList<Task> tasks, Ui ui, Storage storage) throws DukeException, IOException {
        if (description.equals("")) {
            throw new DukeException(ui.EMPTY_DESCRIPTION_RESPONSE);
        }
        Task toDoTask = new ToDo(description);
        tasks.add(toDoTask);
        response = ui.addTodoTaskResponse(toDoTask, tasks);
        storage.save(tasks);
    }
}
