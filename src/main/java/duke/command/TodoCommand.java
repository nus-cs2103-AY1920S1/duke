package duke.command;

import duke.task.Task;
import duke.task.TaskList;
import duke.task.TodoTask;

public class TodoCommand extends CommandAddTask {
    private String dsc;
    public TodoCommand(String dsc) {
        this.dsc = dsc;
    }

    @Override
    public String execute(TaskList tasks) {
        Task addedTask = new TodoTask(this.dsc);
        tasks.addTask(addedTask);
        return createSuccessMsg(addedTask);
    }
}
