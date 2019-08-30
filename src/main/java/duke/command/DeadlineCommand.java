package duke.command;

import duke.util.DateTime;
import duke.task.Deadline;

public class DeadlineCommand extends NewTaskCommand {

    public DeadlineCommand(String description, DateTime by) {
        super(new Deadline(description, by));
    }
}
