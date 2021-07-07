package duke.command;

import duke.exception.InvalidTaskIndexDukeException;
import duke.task.TaskList;

/**
 * A command to set a task to complete in TaskList.
 */
public class DoneCommand extends CommandWithIndex {
    /**
     * Constructs a DoneCommand that set a task to complete in a TaskList when executed.
     *
     * @param index The index of the Task in TaskList.
     */
    public DoneCommand(int index) {
        super(index);
    }

    /**
     * Sets a task in a TaskList to completed.
     *
     * @param tasks The TaskList where the Task to set to complete is.
     * @return A string to inform the user of success in execution.
     */
    @Override
    public String execute(TaskList tasks) {
        try {
            checkIfIndexIsOutOfBounds(tasks);
            tasks.checkTask(this.index);
            return createSuccessfulDoneMsg(tasks);
        } catch (InvalidTaskIndexDukeException e) {
            Command c = new InvalidCommand("Index is out of bounds");
            return c.execute(tasks);
        }
    }

    private String createSuccessfulDoneMsg(TaskList tasks) {
        return "Nice! I've marked this task as done: \n" + tasks.getTaskDsc(this.index);
    }
}
