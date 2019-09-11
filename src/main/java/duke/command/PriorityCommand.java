package duke.command;

import duke.exception.InvalidTaskIndexDukeException;
import duke.task.PriorityLevel;
import duke.task.TaskList;

public class PriorityCommand extends CommandWithIndex {
    private PriorityLevel priority;
    public PriorityCommand(int index, PriorityLevel priority) {
        super(index);
        this.priority = priority;
    }

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
