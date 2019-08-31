/**
 * Deals with making sense of the user command.
 */
public class Parser {

    /**
     * Reads commands give to Duke.
     * Creates the appropriate command object.
     *
     * @param fullCommand Whole command where the first word will be extracted.
     * @return Command object that is created according to the type of command.
     */
    public static Command parse(String fullCommand) {
        String[] currArray = fullCommand.split("\\s+", 2);
        switch (currArray[0]) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "done":
            return new DoneCommand(currArray[1]);
        case "delete":
            return new DeleteCommand(currArray[1]);
        case "find":
            return new FindCommand(currArray[1]);
        case "todo":
            return new AddCommand(Todo.parse(currArray));
        case "deadline":
            return new AddCommand(Deadline.parse(currArray));
        case "event":
            return new AddCommand(Event.parse(currArray));
        case "help":
            return new HelpCommand();
        default:
            throw new DukeException("     OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}