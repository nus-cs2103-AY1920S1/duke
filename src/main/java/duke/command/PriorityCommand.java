package duke.command;

import duke.exception.InvalidTaskIndexDukeException;
import duke.task.PriorityLevel;
import duke.task.TaskList;

/**
 * A command to set the priority of a Task in TaskList.
 */
public class PriorityCommand extends CommandWithIndex {
    private PriorityLevel priority;

    /**
     * Constructs a PriorityCommand that sets the priority of a task in a TaskList when executed.
     *
     * @param index The index of the Task in TaskList.
     * @param priority The PriorityLevel to set the task to.
     */
    public PriorityCommand(int index, PriorityLevel priority) {
        super(index);
        this.priority = priority;
    }

    /**
     * Sets the priority of a task in a TaskList.
     *
     * @param tasks The TaskList where the Task to set priority is.
     * @return A string to inform the user of the success of setting priority.
     */
    @Override
    public String execute(TaskList tasks) {
        try {
            checkIfIndexIsOutOfBounds(tasks);
            tasks.setPriorityOfTask(this.index, this.priority);
            return createSuccessfulPriorityChangeMsg();
        } catch (InvalidTaskIndexDukeException e) {
            Command c = new InvalidCommand("Index is out of bounds");
            return c.execute(tasks);
        }
    }

    private String createSuccessfulPriorityChangeMsg() {
        return "Nice! I've changed the priority of this task to " + this.priority.toString();
    }
}
