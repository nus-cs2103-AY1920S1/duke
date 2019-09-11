package duke.util;

import duke.command.Command;
import duke.command.AddCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.task.Task;
import duke.task.Todo;
import duke.task.Deadline;
import duke.task.Event;
import duke.exception.DukeException;
import java.util.HashMap;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.function.Function;

/**
 * Parses input entered by user.
 */
public class Parser {
    protected static SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy HHmm");

    /**
     * Returns the command as specified by the input.
     * If input cannot be parsed, throws DukeException.
     *
     * @param input Input entered by user.
     * @return Command object specified by input.
     * @throws DukeException If input cannot be parsed.
     */
    public static Command parse(String input) throws DukeException {
        if (input.equals("bye")) {
            return new ExitCommand();
        } 
        if (input.equals("list")) {
            return new ListCommand();
        }
        
        String keyword = input.split(" ")[0];
        switch (keyword) {
        case "done":
            return parseDone(input);
        case "delete":
            return parseDelete(input);
        case "todo":
            return parseTask(input);
        case "deadline":
            return parseTask(input);
        case "event":
            return parseTask(input);
        case "find":
            return parseFind(input);
        default:
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Returns the itemId for the task to be marked done.
     * If input cannot be parsed, throws DukeException.
     *
     * @param input Input entered by user.
     * @return itemId for object to be marked done.
     * @throws DukeException If input has incorrect format.
     */
    public static DoneCommand parseDone(String input) throws DukeException {
        String[] words = input.split(" ");
        int itemId;
        try {
            assert (words.length == 2);
            String command = words[0];
            itemId = Integer.parseInt(words[1]);
        } catch (AssertionError | NumberFormatException e) {
            throw new DukeException("☹ OOPS!!! Incorrect format for done command.");
        }
        
        return new DoneCommand(itemId);
    }

    /**
     * Returns the itemId for the task to be deleted.
     * If input cannot be parsed, throws DukeException.
     *
     * @param input Input entered by user.
     * @return itemId for object to be marked done.
     * @throws DukeException If input has incorrect format.
     */
    public static DeleteCommand parseDelete(String input) throws DukeException {
        String[] words = input.split(" ");
        int itemId;
        try {
            assert (words.length == 2);
            String command = words[0];
            itemId = Integer.parseInt(words[1]);
        } catch (AssertionError | NumberFormatException e) {
            throw new DukeException("☹ OOPS!!! Incorrect format for delete command.");
        }

        return new DeleteCommand(itemId);
    }

    /**
     * Returns the Task to be added to the TaskList.
     * If input cannot be parsed, throws DukeException.
     *
     * @param input Input entered by user.
     * @return Task to be added.
     * @throws DukeException If input has incorrect format.
     */
    public static AddCommand parseTask(String input) throws DukeException {
        String[] words = input.split(" ");
        String type = words[0];

        String info = input.substring(type.length()).trim();
        
        Task task = null;
        switch (type) {
        case "todo":
            String description = info;
            if (description.equals("")) {
                throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
            }

            return new AddCommand(new Todo(description));
        case "deadline":
            String[] args = info.split("/by");
            if (args.length != 2) {
                throw new DukeException("☹ OOPS!!! A deadline must include a description and date.");
            }

            description = args[0].trim();
            if (description.equals("")) {
                throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
            }

            Date date;
            try {
                date = dateFormatter.parse(args[1].trim());
            } catch (ParseException e) {
                throw new DukeException("☹ OOPS!!! Incorrect date format.");
            }

            return new AddCommand(new Deadline(description, date));
        case "event":
            args = info.split("/at");
            if (args.length != 2) {
                throw new DukeException("☹ OOPS!!! A event must include a description and date.");
            }

            description = args[0].trim();
            if (description.equals("")) {
                throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
            }

            try {
                date = dateFormatter.parse(args[1].trim());
            } catch (ParseException e) {
                throw new DukeException("☹ OOPS!!! Incorrect date format.");
            }

            return new AddCommand(new Event(description, date));
        default:
            throw new DukeException("Invalid task input.");
        }
    }

    /**
     * Returns query to match to Tasks.
     * 
     * @param input Input entered by user.
     * @return String containing query.
     */
    public static FindCommand parseFind(String input) {
        String query = input.split("find")[1];
        return new FindCommand(query);
    }
}