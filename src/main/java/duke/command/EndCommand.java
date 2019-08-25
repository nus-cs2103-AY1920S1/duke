package duke.command;

/**
 * Represents a Command to end the reading of user input and data.
 */
public class EndCommand extends Command {

    /**
     * Constructs a new Command where it does terminate the Duke Application.
     */
    public EndCommand() {
        super(true);
    }
}
