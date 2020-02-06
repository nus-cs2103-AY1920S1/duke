package dose.command;

import static dose.task.TaskPriority.getTaskPriority;

import dose.task.Task;
import dose.task.TaskList;
import dose.task.TaskPriority;
import dose.util.Storage;
import dose.util.Ui;
import dose.util.UiMessage;
import dose.util.exception.DoseException;
import dose.util.exception.ExceptionType;
import java.util.NoSuchElementException;

/**
 * Represents a command to add a priority to a task.
 */
public class PriorityCommand extends ModifyTaskCommand {
    public PriorityCommand(String fullCommand) {
        super(fullCommand);
    }

    /**
     * Adds a priority to the task, based on command issued by the user.
     * @param tasks List of tasks.
     * @param ui UI to display to the user.
     * @param storage Object that handles storage of task list to disk.
     * @throws DoseException Application-specific exception thrown during execution.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DoseException {
        Task task = getTaskById(tasks);

        // then, try to get priority
        try {
            String priorityString = s.next().strip();
            TaskPriority priority = getTaskPriority(priorityString);

            // if ok, add priority to task
            task.addPriority(priority);

            // todo: handle invalid priority string (currently, it just removes priority since null)

            String message = UiMessage.TASK_PRIORITISED.getMessage() + task.toString();
            ui.showMessage(message);
        } catch (NoSuchElementException e) {
            // user input after taskId is blank
            throw new DoseException(ExceptionType.TAG_BLANK);
        }
    }
}
