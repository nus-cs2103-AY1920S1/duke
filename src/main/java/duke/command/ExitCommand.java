package duke.command;

/**
 * Data structure to wrap the command to tell Duke to close
 */
public class ExitCommand extends Command {

    /**
     * Constructs the command which tells Duke to close
     */
    public ExitCommand() {
        super(Type.EXIT);
    }
}
