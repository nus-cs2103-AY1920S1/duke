package Parser;

import Commands.*;
import Exceptions.InvalidCommandException;
import Exceptions.InvalidDescriptionException;
import Tasks.Deadline;
import Tasks.Event;
import Tasks.Todo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Parser {
    public static Command parse(String command) throws InvalidDescriptionException, InvalidCommandException {
        String[] splitString = command.split(" ");
        String taskType = splitString[0];
        if (command.equals("list")) {
            return new ListCommand();
        } else if (splitString[0].equals("done")) {
            int taskIndex = Integer.valueOf(splitString[1]) - 1;
            return new DoneCommand(taskIndex);
        } else if (command.equals("bye")) {
            return new ByeCommand();
        } else if (taskType.equals("todo")) {
            String[] todoSplit = command.split("todo ");
            if (todoSplit.length == 1 || todoSplit[1].isEmpty()) {
                throw new InvalidDescriptionException("\u2639 OOPS!!! The description of a todo cannot be empty.");
            }
            return new AddCommand(new Todo(todoSplit[1].trim()));
        } else if (taskType.equals("deadline")) {
            //branch lvl 8 test
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
            String[] deadlineSplit = command.split("/by ");
            String[] temp = deadlineSplit[0].split("deadline ");
            if (temp.length == 1 || temp[1].isEmpty()) {
                throw new InvalidDescriptionException("\u2639 OOPS!!! The description of a deadline cannot be empty.");
            }
            LocalDateTime ldt = LocalDateTime.parse(deadlineSplit[1].trim(), dtf);
            return new AddCommand(new Deadline(temp[1].trim(), ldt, deadlineSplit[1].trim()));
        } else if (taskType.equals("event")) {
            String[] deadlineSplit = command.split("/at ");
            String[] temp = deadlineSplit[0].split("event ");
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
            if (temp.length == 1 || temp[1].isEmpty()) {
                throw new InvalidDescriptionException("\u2639 OOPS!!! The description of a event cannot be empty.");
            }
            LocalDateTime ldt = LocalDateTime.parse(deadlineSplit[1].trim(), dtf);
            return new AddCommand(new Event(temp[1].trim(), ldt, deadlineSplit[1].trim()));
        } else if (taskType.equals("delete")) {
            int taskIndex = Integer.valueOf(splitString[1]) - 1;
            return new DeleteCommand(taskIndex);
        } else {
            throw new InvalidCommandException("\u2639 OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}

