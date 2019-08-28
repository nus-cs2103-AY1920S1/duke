package duke.commands;

/**
 * Implements the add command.
 * @author Lim Yong Shen, Kevin
 */
public abstract class AddCommand extends Command{

    protected static final String SUCCESS_MESSAGE = "Got it. I've added this task:\n"
            + "%s\nNow you have %d task(s) in the list.\n";

}
