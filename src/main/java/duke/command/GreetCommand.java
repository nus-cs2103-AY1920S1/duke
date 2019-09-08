package duke.command;

import util.OutputBuilder;
import util.DukeOutput;


/***
 * <p>
 * Command to greet user upon program start up.
 * </p>
 */
public class GreetCommand implements Command {
    private static final String DUKE_LOGO =
            " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";

    private static final String GREETING =
            DUKE_LOGO + "\n" + "\n"
                    + "Hello! I'm duke.Duke\n"
                    + "What can I do for you?";

    /***
     * <p>
     * Greets user.
     * </p>
     * @return null.
     */
    @Override
    public void execute() {
        OutputBuilder greetingMessage = new OutputBuilder(GREETING);
        DukeOutput.printMessage(greetingMessage);
    }
}
