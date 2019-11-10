package duke.command;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.Todo;
import duke.tasklist.TaskList;
import duke.ui.Message;

/**
 * TodoCommand class used to create a new Todo task.
 */
public class TodoCommand extends Command {

    /** Description of the task. **/
    private String desc;

    /**
     * Initializes a new TodoCommand.
     * @param desc Description of the Todo task.
     */
    public TodoCommand(String desc) {
        this.desc = desc;
    }

    @Override
    public CommandResult execute(TaskList taskList) throws DukeException {
        Task todo = new Todo(desc);
        taskList.add(todo);
        return new CommandResult(Message.showAddSuccess(todo, taskList));
    }
}
