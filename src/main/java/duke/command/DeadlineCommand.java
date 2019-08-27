package duke.command;

import duke.task.Deadline;

import java.util.Date;

public class DeadlineCommand extends AddCommand {

    public static final String COMMAND_WORD = "deadline";

    public DeadlineCommand(String description, Date by) {
        super(new Deadline(description, by));
    }
}
