package duke.command.add;

import duke.command.Command;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.task.Todo;
import duke.ui.Ui;

public class AddTodoCommand extends Command {

    String detail;

    public AddTodoCommand(String detail) {
        this.detail = detail;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Todo newTodo = new Todo(this.detail);
        tasks.add(newTodo);
        ui.printAddSuccess(tasks.getTasks(), newTodo);
    }

    ;

    @Override
    public boolean isExit() {
        return false;
    }

}
