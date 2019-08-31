package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;

/**
 * DoneCommand class sets a task to be done.
 *
 * @author scwaterbear
 */
public class DoneCommand extends Command {

    private int index;

    /**
     * Class Constructor.
     *
     * @param index list task identifier.
     */
    public DoneCommand(int index) {
        this.index = index;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            Task t = tasks.setIsDone(index);
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(t);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(" ☹ OOPS!!! There is no task number " + (index + 1));
        }

        persistState(tasks, storage);
    }
}
