package parser;

import command.Command;
import command.DeadlineCommand;
import command.DeleteCommand;
import command.DoneCommand;
import command.EventCommand;
import command.ExitCommand;
import command.FindCommand;
import command.ListCommand;
import command.ToDoCommand;
import exception.DukeException;

import java.util.Scanner;

/**
 * Deals with making sense of user command.
 */
public class Parser {
    /**
     * Parses user input into command for execution.
     *
     * @param userInput String of input of user
     * @return the command based on user input
     * @throws DukeException if user input is invalid
     */
    public Command parse(String userInput) throws DukeException {
        String[] separatedInputs = userInput.trim().split("\\s+");
        switch (separatedInputs[0].toLowerCase()) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "done":
            //fallthrough
        case "delete":
            if (separatedInputs.length <= 1) {
                throw new DukeException("invalid number");
            }
            Scanner s = new Scanner(separatedInputs[1]);
            if (separatedInputs.length > 2 || !s.hasNextInt()) {
                throw new DukeException("invalid number");
            }
            if (separatedInputs[0].toLowerCase().equals("done")) {
                return new DoneCommand(s.nextInt() - 1);
            } else {
                return new DeleteCommand(s.nextInt() - 1);
            }
        case "todo":
            String description = userInput.trim().substring(separatedInputs[0].length()).trim();
            if (description.equals("")) {
                throw new DukeException("empty todo");
            }
            return new ToDoCommand(description);
        case "deadline":
            if (userInput.indexOf("/by") == -1) {
                throw new DukeException("empty deadline date");
            } else {
                String[] arguments =
                        userInput.trim().substring(separatedInputs[0].length()).trim().split("/by");
                return new DeadlineCommand(arguments);
            }
        case "event":
            if (userInput.indexOf("/at") == -1) {
                throw new DukeException("empty event date");
            } else {
                String[] arguments =
                        userInput.trim().substring(separatedInputs[0].length()).trim().split("/at");
                return new EventCommand(arguments);
            }
        case "find":
            String keyword = userInput.trim().substring(separatedInputs[0].length()).trim();
            return new FindCommand(keyword);
        default:
            throw new DukeException("invalid input");
        }
    }
}
