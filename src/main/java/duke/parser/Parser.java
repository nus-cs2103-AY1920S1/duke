package duke.parser;

import duke.command.*;
import duke.exception.DukeException;

/**
 * The Parser class is used to decode user input. The parser class then retrieves the appropriate
 * command.
 */
public class Parser {

    /**
     * This method is used to retrieve the command from the user input.
     * @return Command Command object after processing user input
     */
    public static Command parse(String input) {
        String[] inputStringArr = input.split(" ");
        String command = inputStringArr[0];
        switch (command) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand(input);
        case "done":
            return new DoneCommand(input);
        case "todo":
            return new TodoCommand(input);
        case "deadline":
            return new DeadlineCommand(input);
        case "event":
            return new EventCommand(input);
        case "delete":
            return new DeleteCommand(input);
        case "find":
            return new FindCommand(input);
        default:
            throw new DukeException("     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
