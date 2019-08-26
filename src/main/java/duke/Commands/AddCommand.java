package duke.Commands;

import duke.DirectProcessor.Ui;
import duke.DukeException;
import duke.Tasks.Deadline;
import duke.Tasks.Event;
import duke.Tasks.Task;
import duke.DirectProcessor.TaskList;
import duke.Tasks.Todo;

/**
 * This is the Command subclass for add new tasks into the target task list.
 * @Extend duke.Commands.Command
 */
public class AddCommand extends Command {

    private String taskName;
    private String taskTime;
    private String taskType;

    /**
     * This is the constructor when the task to add is a todo task.
     * @param taskType This parameter must be "T", to show that task type is Todo.
     * @param taskName The task name.
     */
    public AddCommand(String taskType, String taskName) {
        this.taskName = taskName;
        this.taskType = taskType;
    }

    /**
     * This is the constructor when the task to add is an event or deadline task.
     * @param taskType This parameter should be "E" or "D", to show whether it is an event or a deadline task.
     * @param taskName The task name.
     * @param taskTime The time of the task, should be in the form "dd/MM/yyyy HH:mm:ss" to avoid exception in execute method.
     */
    public AddCommand(String taskType, String taskName, String taskTime) {
        this.taskName = taskName;
        this.taskType = taskType;
        this.taskTime = taskTime;
    }

    /**
     * This method adds the target task to the target task list and let the target user end to print command message.
     * @param tl The target task list to accept execution.
     * @param ui The target user end to print command information.
     * @throws DukeException If the taskName is empty or the taskTime is not in form "dd/MM/yyyy HH:mm:ss".
     */
    @Override
    public void execute(TaskList tl, Ui ui) throws DukeException {
        Task toAdd;
        if (taskType.equals("T")) {
            toAdd = new Todo(taskName);
        } else if (taskType.equals("E")) {
            toAdd = new Event(taskName, taskTime);
        } else {
            toAdd = new Deadline(taskName, taskTime);
        }
        tl.addTask(toAdd);
        ui.showAddMessage(toAdd);
    }

    /**
     * Determines whether this is an exit command.
     * @return boolean false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
