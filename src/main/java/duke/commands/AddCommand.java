package duke.commands;

import duke.directprocessor.Ui;
import duke.DukeException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.directprocessor.TaskList;
import duke.tasks.Todo;

/**
 * This is the Command subclass for add new tasks into the target task list.
 * @Extend duke.commands.Command
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
     * @param taskTime The time of the task, should be in the form "dd/MM/yyyy HH:mm:ss"
     *                 to avoid exception in execute method.
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
    public String execute(TaskList tl, Ui ui) throws DukeException {
        checkNullPointer(tl, ui);
        assert taskType == "T" || taskType == "E" || taskType == "D":
                "taskType is invalid in the AddCommand.";
        Task toAdd;
        if (taskType.equals("T")) {
            toAdd = new Todo(taskName);
        } else if (taskType.equals("E")) {
            toAdd = new Event(taskName, taskTime);
        } else {
            toAdd = new Deadline(taskName, taskTime);
        }
        tl.addTask(toAdd);
        return ui.showAddMessage(toAdd, tl.getTotalNumber());
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
