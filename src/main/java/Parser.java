package duke.parser;

import duke.command.AddCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.PriorityCommand;
import duke.date.DukeDate;
import duke.exception.DukeException;
import duke.exception.InsufficientArgument;
import duke.exception.InvalidCommand;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

/**
 * Represents a parser class that is able to parse the string commands into
 * actual commands.
 */
public class Parser {
    /**
     * Process a string command and return back a command object.
     * 
     * @param fullCommand the full command that the user inputs in string
     * @return a command which could be executed
     */
    public static Command parse(String fullCommand) throws DukeException {
        String[] split = fullCommand.split(" ", 2);
        String[] desc;

        assert split.length != 0 : "Empty command";
        switch (split[0].toLowerCase()) {
        case "list":
            return new ListCommand();
        case "delete":
            verifyDescription(split, 2);
            return new DeleteCommand(Integer.parseInt(split[1]));
        case "done":
            verifyDescription(split, 2);
            return new DoneCommand(Integer.parseInt(split[1]));
        case "todo":
            verifyDescription(split, 2);
            return new AddCommand(new Todo(split[1]));
        case "event":
            verifyDescription(split, 2);
            desc = splitDescription(split[1]);
            return new AddCommand(new Event(desc[0], desc[1]));
        case "deadline":
            verifyDescription(split, 2);
            desc = splitDescription(split[1]);
            return new AddCommand(new Deadline(desc[0], desc[1]));
        case "find":
            verifyDescription(split, 2);
            return new FindCommand(split[1]);
        case "priority":
            verifyDescription(split, 2);
            desc = splitDescription(split[1]);
            return new PriorityCommand(Integer.parseInt(desc[0].strip()), desc[1]);
        case "bye":
            return new ByeCommand();
        default:
            throw new InvalidCommand();
        }
    }

    private static String[] splitDescription(String description) throws InsufficientArgument {
        String[] split = description.split("/", 2);
        if (split.length < 2) {
            throw new InsufficientArgument();
        }

        if (split[1].split(" |/").length == 5) {
            try {
                String[] temp = split[1].split(" ", 2);
                DukeDate dd = new DukeDate(temp[1]);
                temp[1] = dd.toString();
                split[1] = String.join(" ", temp);
            } catch (Exception e) {
                System.err.println("Incorrect date format");
            }
        }

        assert split.length < 2 : "Split function incorrect";
        return split;
    }

    private static void verifyDescription(String[] description, int size) throws InsufficientArgument {
        if (description.length < size) {
            throw new InsufficientArgument();
        }
    }
}