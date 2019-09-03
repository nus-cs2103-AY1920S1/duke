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
            return DoneCommand.process(currArray);
        case "delete":
            return DeleteCommand.process(currArray);
        case "find":
            return FindCommand.process(currArray);
        case "todo":
            return new AddCommand(Todo.process(currArray));
        case "deadline":
            return new AddCommand(Deadline.process(currArray));
        case "event":
            return new AddCommand(Event.process(currArray));
        case "help":
            return new HelpCommand();
        default:
            throw new DukeException("     OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}