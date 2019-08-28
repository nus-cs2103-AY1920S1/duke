package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TextUi;
import duke.task.Deadline;
import duke.task.TaskList;

/**
 * The DeadlineCommand class is used to create new deadlines.
 */
public class DeadlineCommand extends AddCommand {

    /**
     * Construct a new DeadlineCommand with the given command details.
     *
     * @param details   Command details
     */
    public DeadlineCommand(String details) {
        super(details);
    }

    /**
     * Adds a new deadline to the list of tasks using the details contained
     * in the current command.
     *
     * @param tasks             List of tasks
     * @param ui                User interface
     * @param storage           Hard disk storage
     * @throws DukeException    If an exception is thrown by the execute(...)
     *                          method in the parent class AddCommand
     */
    @Override
    public void execute(TaskList tasks, TextUi ui, Storage storage) throws
            DukeException {
        String[] taskDetails = details.split(" /by ");
        tasks.add(new Deadline(taskDetails[0], taskDetails[1]));
        super.execute(tasks, ui, storage);
    }
}
