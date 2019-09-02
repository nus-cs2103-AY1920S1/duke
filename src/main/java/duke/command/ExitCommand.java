package duke.command;

/**
 * The Command which is used to tell Duke to shut down.
 */
public class ExitCommand extends Command {

    /**
     * Constructs the Command which tells Duke to shut down
     */
    ExitCommand() {
        super(Type.COMMAND_EXIT);
    }
}
