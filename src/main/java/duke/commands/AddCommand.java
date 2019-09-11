package duke.commands;

import duke.directprocessor.Ui;
import duke.DukeException;
import duke.tasks.*;
import duke.directprocessor.TaskList;

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
     * @param taskType This parameter must be "T", to show that task type is Todo.
     * @param taskName The task name.
     */
    public AddCommand(TaskType taskType, String taskName) {
        this.taskName = taskName;
        this.taskType = taskType;
    }

    /**
     * This is constructor is called when the new task s an event or deadline task.
     *
     * @param taskType This parameter should be "E" or "D", to show whether it is an event or a deadline task.
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
     * This method adds the target task into the task list.
     *
     * @param tl The target task list to accept execution, it should not be null.
     * @param ui The target user interface to print command information, it should not be null.
     * @throws DukeException If the taskName is empty or the taskTime is not in form "dd/MM/yyyy HH:mm:ss".
     */
    @Override
    public String execute(TaskList tl, Ui ui) throws DukeException {
        checkNullPointer(tl, ui);
        Task toAdd;
        switch (taskType) {
            case T: toAdd = new Todo(taskName); break;
            case D: toAdd = new Deadline(taskName, taskTime); break;
            case E: toAdd = new Event(taskName, taskTime); break;
            default: throw new DukeException("The task type is invalid.");
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
