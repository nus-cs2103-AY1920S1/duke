package parser;

import commands.*;
import exceptions.DukeException;
import exceptions.InvalidCommandException;
import exceptions.InvalidDescriptionException;
import tasks.Deadline;
import tasks.Event;
import tasks.Todo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {

    /**
     * Interprets the user's input and returns the respective command.
     * @param input user's input string
     * @return command according to user's input
     * @throws InvalidDescriptionException empty description of task
     * @throws InvalidCommandException unknown command
     */
    public static Command parse(String input) throws DukeException {
        String[] splitString = input.split(" ");
        String commandType = splitString[0];
        if (input.equals("list")) {
            return new ListCommand();
        } else if (commandType.equals("done")) {
            int taskIndex = Integer.valueOf(splitString[1]) - 1;
            return new DoneCommand(taskIndex);
        } else if (input.equals("bye")) {
            return new ByeCommand();
        } else if (commandType.equals("todo")) {
            return new AddCommand(stringToTodo(input));
        } else if (commandType.equals("deadline")) {
            return new AddCommand(stringToDeadLine(input));
        } else if (commandType.equals("event")) {
            return new AddCommand(stringToEvent(input));
        } else if (commandType.equals("delete")) {
            int taskIndex = Integer.valueOf(splitString[1]) - 1;
            return new DeleteCommand(taskIndex);
        } else if (commandType.equals("find")) {
            StringBuilder builder = new StringBuilder();
            for (int i = 1; i < splitString.length; i++) {
                builder.append(splitString[i] + " ");
            }
            return new FindCommand(builder.toString().trim());
        } else if (commandType.equals("edit")) {
            return stringToEditCmd(input);
        } else {
            throw new InvalidCommandException("\u2639 OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    private static Todo stringToTodo(String input) throws InvalidDescriptionException {
        String[] todoSplit = input.split("todo ");
        if (todoSplit.length == 1 || todoSplit[1].isEmpty()) {
            throw new InvalidDescriptionException("\u2639 OOPS!!! The description of a todo cannot be empty.");
        }
        return new Todo(todoSplit[1].trim());
    }

    private static Deadline stringToDeadLine(String input) throws DukeException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
        String[] deadlineSplit = input.split("/by ");
        String[] temp = deadlineSplit[0].split("deadline ");
        if (temp.length == 1 || temp[1].isEmpty()) {
            throw new InvalidDescriptionException("\u2639 OOPS!!! The description of a deadline cannot be empty.");
        }
        if (deadlineSplit.length < 2) {
            throw new InvalidDescriptionException("\u2639 OOPS!!! The date of a deadline cannot be empty.");
        }
        try {
            LocalDateTime ldt = LocalDateTime.parse(deadlineSplit[1].trim(), dtf);
            return new Deadline(temp[1].trim(), ldt, deadlineSplit[1].trim());
        } catch (DateTimeParseException e) {
            throw new DukeException("Please enter a valid date in the format DD/MM/YYYY HHmm");
        }
    }

    private static Event stringToEvent(String input) throws DukeException{
        try {
            String[] eventSplit = input.split("/at ");
            String[] temp = eventSplit[0].split("event ");
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
            if (temp.length == 1 || temp[1].isEmpty()) {
                throw new InvalidDescriptionException("\u2639 OOPS!!! The description of a event cannot be empty.");
            }
            if (eventSplit.length < 2) {
                throw new InvalidDescriptionException("\u2639 OOPS!!! The date of a event cannot be empty.");
            }
            LocalDateTime ldt = LocalDateTime.parse(eventSplit[1].trim(), dtf);
            return new Event(temp[1].trim(), ldt, eventSplit[1].trim());
        } catch (DateTimeParseException e) {
            throw new DukeException("Please enter a valid date in the format DD/MM/YYYY HHmm");
        }
    }

    private static EditCommand stringToEditCmd(String input) throws DukeException {
        String[] splitString = input.split(" ");
        if (splitString.length < 3) {
            throw new InvalidDescriptionException("\u2639 OOPS!!! The description of a task cannot be empty.");
        }
        StringBuilder descriptionBuilder = new StringBuilder();
        for (int i = 2; i < splitString.length; i++) {
            descriptionBuilder.append(splitString[i] + " ");
        }
        int taskIndex = Integer.valueOf(splitString[1]) - 1;
        return new EditCommand(taskIndex, descriptionBuilder.toString().trim());
    }
}

