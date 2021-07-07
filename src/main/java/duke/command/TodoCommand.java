package duke.command;

import duke.task.Task;
import duke.task.TaskList;
import duke.task.TodoTask;

/**
 * Command to create a TodoTask.
 */
public class TodoCommand extends CommandAddTask {
    private String dsc;

    /**
     * Constructs a TodoCommand that creates an TodoTask in a TaskList when executed.
     *
     * @param dsc The description of the TodoTask that will be created by TodoCommand.
     */
    public TodoCommand(String dsc) {
        this.dsc = dsc;
    }

    /**
     * Creates a TodoTask in a TaskList.
     *
     * @param tasks The TaskList to create the TodoTask in.
     * @return A string to inform the user that the TodoTask has been created.
     */
    @Override
    public String execute(TaskList tasks) {
        Task addedTask = new TodoTask(this.dsc);
        tasks.addTask(addedTask);
        return createSuccessMsg(addedTask);
    }
}
