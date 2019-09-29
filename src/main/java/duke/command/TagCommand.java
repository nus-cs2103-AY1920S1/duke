package duke.command;

import duke.task.Task;
import duke.task.TaskList;
import duke.util.Ui;
import duke.util.exception.DukeException;
import duke.util.exception.ExceptionType;
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
     * @throws DukeException Application-specific exception thrown during execution.
     */
    @Override
    public void execute(TaskList tasks, Ui ui) throws DukeException {
        Task task = getTaskById(tasks);

        // then, try to get tag
        try {
            String tag = s.next().strip();

            // if ok, add tag to task
            task.addTag(tag);
        } catch (NoSuchElementException e) {
            // user input after taskId is blank
            throw new DukeException(ExceptionType.TAG_BLANK);
        }
    }
}
