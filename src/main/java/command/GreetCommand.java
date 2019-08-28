package command;

import java.util.Optional;

import util.DukeMessage;
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
                    + "Hello! I'm Duke\n"
                    + "What can I do for you?";

    /***
     * <p>
     * Greets user.
     * </p>
     * @return null.
     */
    @Override
    public Optional<Command> execute() {
        DukeMessage greetingMessage = new DukeMessage(GREETING);
        DukeOutput.printMessage(greetingMessage);
        return Optional.empty();
    }
}
