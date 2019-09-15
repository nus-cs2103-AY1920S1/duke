package command;

import task.Task;
import util.Storage;
import util.TodoList;

public class UpdateCommand extends Command {
    private int index;
    private Task newTask;

    public UpdateCommand(int index, Task newTask) {
        this.index = index;
        this.newTask = newTask;
    }

    @Override
    public String run(TodoList tasks, Storage storage) {
        Task oldTask = tasks.update(index, newTask);
        return getResponse(oldTask);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    private String getResponse(Task oldTask) {
        return String.join("\n", "Updated task from:", oldTask.toString(), "To:", newTask.toString());
    }
}
