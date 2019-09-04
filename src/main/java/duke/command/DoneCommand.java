package duke.command;

import duke.exception.InvalidTaskIndexDukeException;
import duke.task.TaskList;

public class DoneCommand extends CommandWithIndex {
    public DoneCommand(int index) {
        super(index);
    }

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
