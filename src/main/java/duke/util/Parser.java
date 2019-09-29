package duke.util;

import duke.command.*;
import duke.util.exception.DukeException;
import duke.util.exception.ExceptionType;
import java.util.Scanner;

public class Parser {

    public static Command parse(String fullCommand) throws DukeException {
        Scanner commandReader  = new Scanner(fullCommand);
        String command = commandReader.next();

        if (command.equals("bye")) {
            return new ExitCommand();
        } else if (command.equals("list")) {
            return new ListCommand();
        } else if (command.equals("done")) {
            return new DoneCommand(fullCommand);
        } else if (command.equals("delete")) {
            return new DeleteCommand(fullCommand);
        } else if (command.equals("find")) {
            return new FindCommand(fullCommand);
        } else if (command.equals("todo")) {
            return new AddTodoCommand(fullCommand);
        } else if (command.equals("event")) {
            return new AddEventCommand(fullCommand);
        } else if (command.equals("deadline")) {
            return new AddDeadlineCommand(fullCommand);
        } else if (command.equals("snooze")) {
            return new SnoozeCommand(fullCommand);
        } else if (command.equals("tag")) {
            return new TagCommand(fullCommand);
        } else {
            throw new DukeException(ExceptionType.INVALID_COMMAND);
        }
    }
}
