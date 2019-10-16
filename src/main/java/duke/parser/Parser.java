package duke.parser;

import duke.command.*;
import duke.exception.DukeException;
import static duke.ui.Message.INVALID_INPUT_ERROR;

/**
 * Represents a parser to parse user command.
 */
public class Parser {

    public static Command parse(String fullCommand) throws DukeException {
        String[] description = fullCommand.trim().split(" ", 2);
        String command = description[0];
        if (command.equals("help")) {
            return new HelpCommand(description);
        } else if (command.equals("todo")) {
            return new AddTodoCommand(description);
        } else if (command.equals("event")) {
            return new AddEventCommand(description);
        } else if (command.equals("deadline")) {
            return new AddDeadlineCommand(description);
        } else if (command.equals("delete")) {
            return new DeleteCommand(description);
        } else if (command.equals("done")) {
            return new DoneCommand(description);
        } else if (command.equals("find")) {
            return new FindCommand(description);
        } else if (command.equals("list")) {
            return new ListCommand(description);
        } else if (command.equals("bye")) {
            return new ExitCommand(description);
        } else {
            throw new DukeException(INVALID_INPUT_ERROR);
        }
    }
}
