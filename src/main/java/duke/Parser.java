package duke;

import command.Command;
import command.FindCommand;
import command.ListCommand;
import command.DeleteCommand;
import command.DoneCommand;
import command.AddCommand;
import command.EndCommand;

import exception.DukeException;

import exception.InvalidCommandException;
import exception.MissingArgumentsException;
import task.Deadline;
import task.Todo;
import task.Event;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Parses the command string from the user and check its validity.
 */
public class Parser {
    /**
     * Parse the command string given.
     * @param command Command string.
     * @return Command object.
     * @throws DukeException Invalid actions / missing values.
     * @throws ParseException Invalid variable to parse.
     */
    public static Command parse(String command) throws DukeException, ParseException {
        String[] task = command.split(" ", 2);
        int taskSize = task.length;

        //action required
        command = task[0];

        switch (command) {
        case "list":
            return new ListCommand();
        case "done":
            return new DoneCommand(Integer.parseInt(task[1].trim()));
        case "bye":
            return new EndCommand();
        case "delete":
        case "todo":
        case "find":
            if (taskSize < 2) {
                throw new MissingArgumentsException(command);
            } else {
                assert task[1].isEmpty() : "Switch case flow error.";
                return addCommand(command, task[1]);
            }
        case "deadline":
        case "event":
            if (taskSize >= 2 && (task[1].contains("/by") || task[1].contains(("/at")))) {
                return addCommand(command, task[1]);
            } else {
                throw new MissingArgumentsException(command);
            }
        default:
            throw new InvalidCommandException();
        }
    }

    private static Command addCommand(String command, String description) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        switch (command) {
            case "delete":
                return new DeleteCommand(Integer.parseInt(description.trim()));
            case "todo":
                return new AddCommand(new Todo(description.trim()));
            case "find":
                return new FindCommand(description);
            case "deadline":
                assert description.contains("/by") : "Missing arguments for deadline";
                String[] byLimiter = description.split(" /by ", 2);
                return new AddCommand(new Deadline(byLimiter[0].trim(), formatter.parse(byLimiter[1])));
            case "event":
                assert description.contains("/at") : "Missing arguments for event";
                String[] atLimiter = description.split(" /at ", 2);
                return new AddCommand(new Event(atLimiter[0].trim(), formatter.parse(atLimiter[1])));
            default:
                return null;
        }
    }
}
