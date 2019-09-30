package dose.util;

import dose.command.*;
import dose.util.exception.DoseException;
import dose.util.exception.ExceptionType;
import java.util.Scanner;

public class Parser {

    public static Command parse(String fullCommand) throws DoseException {
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
        } else if (command.equals("priority")) {
            return new PriorityCommand(fullCommand);
        } else if (command.equals("save")) {
            return new SaveCommand(); // disregard any input after "save"
        } else {
            throw new DoseException(ExceptionType.INVALID_COMMAND);
        }
    }
}
