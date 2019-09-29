package duke.util;

import duke.command.*;
import duke.exception.DukeException;
import duke.exception.ExceptionType;
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
            String restOfCommand = commandReader.nextLine();
            return new AddEventCommand(restOfCommand);
        } else if (command.equals("deadline")) {
            String restOfCommand = commandReader.nextLine();
            return new AddDeadlineCommand(restOfCommand);
        } else {
            throw new DukeException(ExceptionType.INVALID_COMMAND);
        }
    }
}
