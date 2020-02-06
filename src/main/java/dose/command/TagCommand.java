package dose.command;

import dose.task.Task;
import dose.task.TaskList;
import dose.util.Storage;
import dose.util.Ui;
import dose.util.UiMessage;
import dose.util.exception.DoseException;
import dose.util.exception.ExceptionType;
import java.util.NoSuchElementException;

/**
 * Represents a command to add a tag to a task.
 */
public class TagCommand extends ModifyTaskCommand {
    public TagCommand(String fullCommand) {
        super(fullCommand);
    }

    /**
     * Adds a tag to the task, based on command issued by the user.
     * @param tasks List of tasks.
     * @param ui UI to display to the user.
     * @param storage Object that handles storage of task list to disk.
     * @throws DoseException Application-specific exception thrown during execution.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DoseException {
        Task task = getTaskById(tasks);

        // then, try to get tag
        try {
            String tag = s.next().strip();

            // if ok, add tag to task
            task.addTag(tag);
            String message = UiMessage.TASK_TAGGED.getMessage() + task.toString();
            ui.showMessage(message);
        } catch (NoSuchElementException e) {
            // user input after taskId is blank
            throw new DoseException(ExceptionType.TAG_BLANK);
        }
    }
}
