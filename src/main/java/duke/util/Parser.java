package duke.util;

import duke.command.*;

public class Parser {

    /**
     * Parses the user's input and directs it to the appropriate command.
     *
     * @param fullCommand The entirety of the user's input
     */
    public static Command parse(String fullCommand) {
        if (fullCommand.equals("bye")) {
            return new ExitCommand();
        } else if (fullCommand.equals("list")) {
            return new ListCommand();
        } else if (fullCommand.contains("done")) {
            return new DoneCommand();
        } else if (fullCommand.contains("delete")) {
            return new DeleteCommand();
        } else if (fullCommand.contains("todo") || fullCommand.contains("deadline") || fullCommand.contains("event")) {
            return new AddCommand();
        } else if (fullCommand.contains("find")) {
            return new FindCommand();
        } else if (fullCommand.contains("help")) {
            return new HelpCommand();
        } else if (fullCommand.contains("stats")) {
            return new StatsCommand();
        }
        return new InvalidCommand();
    }

}
