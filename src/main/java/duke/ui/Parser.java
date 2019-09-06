package duke.ui;

import duke.command.TaskCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.AddCommand;
import duke.exception.DukeException;
import duke.person.Person;
import duke.person.PersonList;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

import java.util.Arrays;

public class Parser {
    /**
     * parses the command string and create according type of command.
     *
     * @param fullCommand string of command
     * @return return a new command
     * @throws DukeException when command is invalid
     */
    public static TaskCommand parse(String fullCommand) throws DukeException {
        String[] parts = fullCommand.split("[|]");
        if (fullCommand.equals("bye")) {
            return new ExitCommand();
        } else if (parts.length == 0) {
            throw new DukeException("Your input cannot be empty.");
        }

        switch (parts[0]) {
        case "list":
            return parseList(parts);
        case "done":
            return parseDone(parts);
        case "delete":
            return parseDelete(parts);
        case "todo":
            return parseTodo(parts);
        case "deadline":
            return parseDeadline(parts);
        case "event":
            return parseEvent(parts);
        case "find":
            return parseFind(parts);
        default:
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }

    private static TaskCommand parseList(String[] parts) {
        return new ListCommand();
    }

    private static TaskCommand parseDone(String[] parts) throws DukeException {
        if (parts.length == 1) {
            throw new DukeException("The description of a done cannot be empty.");
        }
        int number = Integer.parseInt(parts[1]);
        return new DoneCommand(number);
    }

    private static TaskCommand parseDelete(String[] parts) throws DukeException {
        if (parts.length == 1) {
            throw new DukeException("The description of a delete cannot be empty.");
        }
        int number = Integer.parseInt(parts[1]);
        return new DeleteCommand(number);
    }

    private static TaskCommand parseTodo(String[] parts) throws DukeException {
        if (parts.length == 1) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
        String description = parts[1];
        PersonList list = parsePersonList(Arrays.copyOfRange(parts, 2, parts.length));
        Task todoTask = new Todo(description, list);
        return new AddCommand(todoTask);
    }

    private static TaskCommand parseDeadline(String[] parts) throws DukeException {
        if (parts.length == 1) {
            throw new DukeException("The description of a deadline cannot be empty.");
        } else if (parts.length <= 2) {
            throw new DukeException("Please provide a time for your deadline task");
        }
        String description = parts[1];
        String date = parts[2];
        PersonList list = parsePersonList(Arrays.copyOfRange(parts, 3, parts.length));
        Task deadlineTask = new Deadline(description, date, list);
        return new AddCommand(deadlineTask);
    }

    private static TaskCommand parseEvent(String[] parts) throws DukeException {
        if (parts.length == 1) {
            throw new DukeException("The description of a deadline cannot be empty.");
        } else if (parts.length <= 2) {
            throw new DukeException("Please provide a time for your deadline task");
        }
        String description = parts[1];
        String date = parts[2];
        PersonList personList = parsePersonList(Arrays.copyOfRange(parts, 3, parts.length));
        Task deadlineTask = new Event(description, date, personList);
        return new AddCommand(deadlineTask);
    }

    private static TaskCommand parseFind(String[] parts) throws DukeException {
        if (parts.length == 1) {
            throw new DukeException("Please provide the string you want to search in the duke.tasks");
        }
        String description = parts[1];
        return new FindCommand(description);
    }

    private static PersonList parsePersonList(String[] parts) {
        PersonList list = new PersonList();
        for (String str : parts) {
            int startIndex = str.indexOf("(");
            int endIndex = str.lastIndexOf(")");
            if (startIndex == -1) {
                String name = str;
                list.addPerson(new Person(name));
            } else {
                String name = str.substring(0, startIndex);
                String title = str.substring(startIndex + 1, endIndex);
                if (endIndex == str.length() - 1) {
                    list.addPerson(new Person(name, title));
                } else {
                    String contact = str.substring(endIndex + 1);
                    list.addPerson(new Person(name, title, contact));
                }
            }
        }
        return list;
    }

}
