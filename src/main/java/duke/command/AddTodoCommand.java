package duke.command;

import duke.data.Storage;
import duke.exception.DukeException;
import duke.object.Task;
import duke.object.TaskList;
import duke.user.Ui;

import java.io.IOException;

public class AddTodoCommand extends Command {
    private String todo;

    public AddTodoCommand(String todo) {
        if (todo.equals("")) {
            throw new DukeException("    ☹ OOPS!!! The description of a todo cannot be empty.");
        }
        this.todo = todo;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        ui.confirmTodoAdded(tasks.addTask(new Task('T', this.todo)), tasks);
        storage.saveTasks(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
