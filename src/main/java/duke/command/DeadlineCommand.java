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
     * @param details   Command details.
     */
    public DeadlineCommand(String details) {
        super(details);
    }

    /**
     * Adds a new deadline to the list of tasks using the details contained
     * in the current command.
     *
     * @param tasks             List of tasks.
     * @param ui                User interface.
     * @param storage           Hard disk storage.
     * @return                  String containing Duke's response.
     */
    @Override
    public String execute(TaskList tasks, TextUi ui, Storage storage) {
        String[] taskDetails = details.split(" /by ");
        tasks.add(new Deadline(taskDetails[0], taskDetails[1]));
        return super.execute(tasks, ui, storage);
    }
}
