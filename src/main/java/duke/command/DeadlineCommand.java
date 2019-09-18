package duke.command;

import duke.DukeException;
import duke.task.Deadline;

/**
 * Handles the command to add a deadline to the list of tasks.
 */
public class DeadlineCommand extends AddCommand {

    public DeadlineCommand(String description, String by) throws DukeException {
        super(new Deadline(description, by));
    }

    public DeadlineCommand(String description, String by, int freq) throws DukeException {
        super(new Deadline(description, by, freq));
    }
}
