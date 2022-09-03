package duke.commands;

import duke.directprocessor.Ui;
import duke.DukeException;
import duke.directprocessor.TaskList;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.TaskType;
import duke.tasks.Todo;

/**
 * This is the specific command used to add a new task into the task list.
 */
public class AddCommand extends Command {

    private String taskName;
    private String taskTime;
    private TaskType taskType;

    /**
     * This constructor is called  when the new task is a todo task.
     *
     * @param taskType The type of the task, in this case it is TaskType.T.
     * @param taskName The task name as a string.
     */
    public AddCommand(TaskType taskType, String taskName) {
        this.taskName = taskName;
        this.taskType = taskType;
    }

    /**
     * This is constructor is called when the new task s an event or deadline task.
     *
     * @param taskType The type of the task, in this case, it is either TaskType.E or TaskType.D.
     * @param taskName The task name.
     * @param taskTime The time of the task, should be in the form "dd/MM/yyyy HH:mm:ss"
     *                 to avoid exception while execute method.
     */
    public AddCommand(TaskType taskType, String taskName, String taskTime) {
        this.taskName = taskName;
        this.taskType = taskType;
        this.taskTime = taskTime;
    }

    /**
     * Call the task list to add the new task.
     * Call the user interface to generate command execution message as a string.
     *
     * @param tl The target task list to accept the command.
     * @param ui The target user interface to generate the command information as a String.
     * @return The task message of the newly added task.
     * @throws DukeException when the input task type, task name or task time is invalid.
     */
    @Override
    public String execute(TaskList tl, Ui ui) throws DukeException {
        checkNullPointer(tl, ui);
        Task toAdd;
        switch (taskType) {
        case T:
            toAdd = new Todo(taskName);
            break;
        case D:
            toAdd = new Deadline(taskName, taskTime);
            break;
        case E:
            toAdd = new Event(taskName, taskTime);
            break;
        default:
            throw new DukeException("The task type is invalid.");
        }
        tl.addTask(toAdd);
        return ui.showAddMessage(toAdd, tl.getTotalNumber());
    }

    /**
     * Determines whether this is an exit command.
     *
     * @return boolean false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
