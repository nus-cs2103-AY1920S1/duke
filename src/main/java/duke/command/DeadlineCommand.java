package duke.command;

import duke.DukeException;
import duke.task.Deadline;

public class DeadlineCommand extends AddCommand {

    public DeadlineCommand(String description, String by) throws DukeException {
        super(new Deadline(description, by));
    }

}
