package duke;

import command.AddCommand;
import command.ByeCommand;
import command.ClearCommand;
import command.Command;
import command.DeleteCommand;
import command.DoneCommand;
import command.FindCommand;
import command.ListCommand;
import command.TriviaCommand;

/**
 * Parser class to parse a given input command.
 */
public class Parser {

    /**
     * To parse the given user command to be recognized.
     *
     * @param fullCommand full command input by user.
     * @return A Command which is able to be executed.
     * @throws DukeException If command is invalid.
     */
    public static Command parse(String fullCommand) throws DukeException {
        String[] arr = fullCommand.split(" ", 2);
        String cmd = arr[0];
        Command command;
        switch (cmd) {
        case "todo":
        case "deadline":
        case "event":
            command = new AddCommand(arr);
            break;
        case "done":
            DukeException.checkValidity(arr.length < 2,
                    "An Integer is required to choose the task.");
            command = new DoneCommand(arr[1]);
            break;
        case "delete":
            DukeException.checkValidity(arr.length < 2,
                    "An Integer is required to choose the task.");
            command = new DeleteCommand(arr[1]);
            break;
        case "list":
            command = new ListCommand();
            break;
        case "bye":
            command = new ByeCommand();
            break;
        case "find":
            DukeException.checkValidity(arr.length < 2,
                    "An expression is required to find the task.");
            command = new FindCommand(arr[1]);
            break;
        case "clear":
            command = new ClearCommand();
            break;
        case "trivia":
            if (arr.length < 2) {
                command = new TriviaCommand();
            } else {
                command = new TriviaCommand(arr[1]);
            }
            break;
        default:
            throw new DukeException("I'm sorry, but I don't know what that means.");
        }
        return command;
    }
}
