import exception.DukeException;

/**
 * Handles the user command.
 *
 * @author Michelle Yong
 */
public class Parser {
    public Parser() { }

    // Features to add: help, tag, priority, edit, undo
    /**
     * Gets the command type of the task.
     *
     * @param input The input by the user.
     * @return The command type.
     * @throws DukeException If the command by the user is invalid.
     */
    public static Command getCommand(String input) throws DukeException {
        String[] inputArr = input.split(" ");
        String command = inputArr[0];
        switch (command) {
        case "todo":
            return new TodoCommand(input);
        case "deadline":
            return new DeadlineCommand(input);
        case "event":
            return new EventCommand(input);
        case "list":
            return new ListCommand();
        case "done":
            return new DoneCommand(input);
        case "bye":
            return new ByeCommand();
        case "delete":
            return new DeleteCommand(input);
        case "find":
            return new FindCommand(input);
        case "priority":
            return new PriorityCommand(input);
        case "clear":
            return new ClearCommand();
        case "help":
            return new HelpCommand();
        default:
            throw new DukeException();
        }
    }
}