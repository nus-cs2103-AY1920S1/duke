package duke.command;

import duke.util.exception.DukeException;
import duke.util.exception.ExceptionType;
import duke.task.TaskList;
import duke.util.Ui;

public class ListCommand implements Command {

    /**
     * Lists all tasks currently in the list.
     * @param tasks List of tasks.
     * @param ui UI to display to the user.
     * @throws DukeException Application-specific exception thrown during execution.
     */
    @Override
    public void execute(TaskList tasks, Ui ui) throws DukeException {
        if (tasks.isEmpty()) {
            throw new DukeException(ExceptionType.TASK_LIST_EMPTY);
        }
        tasks.printList();
    }
}
