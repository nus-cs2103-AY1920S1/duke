/**
 * Responsible for interpreting Commands
 */
public class Parser {

    /**
     * Checks user input for a Command and returns the associated Command class
     * @param userCmd The command from user input
     * @return Command object
     * @throws DukeException If user input is an invalid command
     */
    public static Command parse(String userCmd) throws DukeException {
        switch (userCmd) {
        case "list":
            return new ListCommand();
        case "done":
            return new DoneCommand();
        case "delete":
            return new DeleteCommand();
        case "exit":
            return new ExitCommand();
        case "todo":
        case "event":
        case "deadline":
            return new AddCommand(userCmd);
        default:
            throw new DukeException("Invalid Command");
        }
    }
}
