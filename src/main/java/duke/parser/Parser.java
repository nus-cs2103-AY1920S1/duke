package duke.parser;

import duke.command.*;
import duke.exception.DukeException;

public class Parser {

    /**
     * Parses any user inputs and determines the type of command that was entered.
     *
     * @param fullCommand The command input by user
     * @return The type of command entered
     * @throws DukeException In the event that a command has an incorrect format/ empty description.
     */
    public static Command parse(String fullCommand) throws DukeException {
        //Parse the string to check which command it is referring to
        String[] details = fullCommand.trim().split("\\s+");

        try {
            switch ( details[0] ) {
            case "list":
                return new ListCommand();
            case "find":
                return new FindCommand(details[1]);
            case "done":
                return new DoneCommand(Integer.parseInt(details[1]) - 1);
            case "delete":
                return new DeleteCommand(Integer.parseInt(details[1]) - 1);
            case "todo":
            case "deadline":
            case "event":
                return new AddCommand(details[0], fullCommand.split(details[0]));
            case "bye":
                return new ExitCommand();
            default:
                throw new DukeException("I'm sorry, but I don't know what that means :-( ");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            // If the command misses a description entirely
            switch ( details[0] ) {
            case "done":
            case "delete":
                throw new DukeException("Incorrect format. Missing a task index. \nPlease try again with the following format: " + details[0] + " 3");
            case "find":
                throw new DukeException("Incorrect format. Missing a keyword to find. \nPlease try again with the following format: " + details[0] + " book");
            default:
                throw new DukeException("Incorrect format. Please try again.");
            }
        } catch (NumberFormatException e) {
            // If the delete/done commands are followed by strings instead of a number index
            throw new DukeException("Incorrect format. \n" + details[0] + " should be followed by a number index not a string. \ne.g. " + details[0] + " 3");
        }
    }
}
