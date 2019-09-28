/**
 * Represents a parser that needs to make sense of the user input.
 */
public class DukeParser {
    /**
     * Constructs a Duke Object.
     */
    public DukeParser() {

    }

    /**
     * Makes sense of the String given to the parser.
     *
     * @param input The user input
     * @return returns one the possible commands interpreted from the user input.
     */
    public static Command parse(String input) {
        String command = input.split(" ")[0].trim(); //get the first word;
        switch (command) {
        case "bye":
            return new ByeCommand(input);
        case "list":
            return new ListCommand(input);
        case "done":
            return new DoneCommand(input);
        case "delete":
            return new DeleteCommand(input);
        case "todo":
            return new ToDoCommand(input);
        case "deadline":
            return new DeadlineCommand(input);
        case "event":
            return new EventCommand(input);
        case "help":
            return new HelpCommand(input);
        case "find":
            return new FindCommand(input);
        default:
            throw new InvalidCommandDukeException(command);
        }
    }
}
