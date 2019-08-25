package ui;

import command.Command;
import exception.DukeException;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

public class Parser {
    public static Command parse(String fullCommand) throws DukeException {
        String[] parts = fullCommand.split(" ");
        int number, index;
        String description, date;
        if(fullCommand.equals("bye")) return new Command(Command.Operation.EXIT,null);
        else if (parts.length == 0) throw new DukeException("Your input cannot be empty.");

        switch (parts[0]) {
        case "list":
            return new Command(Command.Operation.LIST);
        case "done":
            if (parts.length == 1) throw new DukeException("The description of a done cannot be empty.");
            number = Integer.parseInt(parts[1]);
            return new Command(Command.Operation.MARKDONE, number);
        case "delete":
            if (parts.length == 1) throw new DukeException("The description of a delete cannot be empty.");
            number = Integer.parseInt(parts[1]);
            return new Command(Command.Operation.DELETE, number);
        case "todo":
            if (parts.length == 1) throw new DukeException("The description of a todo cannot be empty.");
            description = fullCommand.substring("todo".length() + 1);
            Task todoTask = new Todo(description);
            return new Command(Command.Operation.ADD, todoTask);
        case "deadline":
            if (parts.length == 1) throw new DukeException("The description of a deadline cannot be empty.");
            index = fullCommand.indexOf("/");
            if (index == -1 || index + 4 >= fullCommand.length()) throw new DukeException("Please provide a time for your deadline task");
            if ("deadline".length() + 1 >= index - 1) throw new DukeException("Please provide a proper name for your deadline task");
            description = fullCommand.substring("deadline".length() + 1, index - 1);
            date = fullCommand.substring(index + 4);
            Task deadlineTask = new Deadline(description, date);
            return new Command(Command.Operation.ADD, deadlineTask);
        case "event":
            if (parts.length == 1) throw new DukeException("The description of a event cannot be empty.");
            index = fullCommand.indexOf("/");
            if (index == -1 || index + 4 >= fullCommand.length()) throw new DukeException("Please provide a time for your event task");
            if ("event".length() + 1 >= index - 1) throw new DukeException("Please provide a proper name for your event task");
            description = fullCommand.substring("event".length() + 1, index - 1);
            date = fullCommand.substring(index + 4);
            Task eventTask = new Event(description, date);
            return new Command(Command.Operation.ADD, eventTask);
        default:
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }
}
