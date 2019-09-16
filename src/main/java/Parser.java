import exception.DukeException;

import java.util.Scanner;

/**
 * Handles the user command.
 */
public class Parser {
    private Scanner sc;

    public Parser() {
        Scanner sc = new Scanner(System.in);
        this.sc = sc;
    }

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
            return new ListCommand(input);
        case "done":
            return new DoneCommand(input);
        case "bye":
            return new ByeCommand(input);
        case "delete":
            return new DeleteCommand(input);
        case "find":
            return new FindCommand(input);
        default:
            throw new DukeException();
        }
    }
}