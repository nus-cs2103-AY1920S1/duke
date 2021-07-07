package duke.command;

import duke.task.TaskList;

/**
 * A command that informs the user of invalid input.
 */
public class InvalidCommand extends Command implements UnloadableCommand {
    private String reasonForInvalid;

    /**
     * Constucts an InvalidCommand that contains the reason for invalid input.
     * @param reasonForInvalid A string that explains why input is invalid.
     */
    public InvalidCommand(String reasonForInvalid) {
        this.reasonForInvalid = reasonForInvalid;
    }

    /**
     * Returns a string that explains why input is invalid.
     * @param tasks The TaskList of duke.
     * @return A string that explains why input is invalid.
     */
    @Override
    public String execute(TaskList tasks) {
        return this.reasonForInvalid;
    }
}
