package duke.commands;

/**
 * Provides a framework for commands to be built upon,
 * specifically those that add to the list of tasks.
 * @author Lim Yong Shen, Kevin
 */
public abstract class AddCommand extends Command{

    protected static final String SUCCESS_MESSAGE = "Got it. I've added this task:\n"
            + "%s\nNow you have %d task(s) in the list.\n";

}
