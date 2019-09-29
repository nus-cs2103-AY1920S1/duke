package duke.util;

import duke.command.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Parser {

    public static Command parse(String fullCommand) throws InputMismatchException {
        Scanner commandReader  = new Scanner(fullCommand);
        String command = commandReader.next();

        if (command.equals("bye")) {
            return new ExitCommand();
        } else if (command.equals("list")) {
            return new ListCommand();
        } else if (command.equals("done")) {
            int taskId = commandReader.nextInt(); // extract the task ID entered by user
            return new DoneCommand(taskId);
        } else if (command.equals("delete")) {
            int taskId = commandReader.nextInt();
            return new DeleteCommand(taskId);
        } else if (command.equals("find")) {
            String keyword = commandReader.nextLine();
            return new FindCommand(keyword);
        } else if (command.equals("todo")) {
            // todo: error handling for empty restOfCommand :(
            String restOfCommand = commandReader.nextLine();
            return new AddTodoCommand(restOfCommand);
        } else if (command.equals("event")) {
            String restOfCommand = commandReader.nextLine();
            return new AddEventCommand(restOfCommand);
        } else if (command.equals("deadline")) {
            String restOfCommand = commandReader.nextLine();
            return new AddDeadlineCommand(restOfCommand);
        } else {
            throw new InputMismatchException();
        }
    }
}
