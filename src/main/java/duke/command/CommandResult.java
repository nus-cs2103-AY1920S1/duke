package duke.command;

import static java.util.Objects.requireNonNull;

/**
 * Represents a non-null result from executing the {@link Command}.
 */
public class CommandResult {
    public final String feedback;

    public CommandResult(String feedback) {
        this.feedback = requireNonNull(feedback);
    }
}
