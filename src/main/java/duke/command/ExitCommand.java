package duke.command;

/**
 * The Command which is used to instruct Duke to stop accepting input.
 */
public class ExitCommand extends Command {

    /**
     * Constructs the Command which is used to instruct Duke to stop accepting input.
     */
    ExitCommand() {
        super(Type.COMMAND_EXIT);
    }
}
