package utils;

import error.DukeException;
import command.Command;
import command.AddCommand;
import command.UpdateCommand;
import command.DeleteCommand;
import command.FindCommand;
import command.ListCommand;
import command.ExitCommand;
import command.ListAlias;
import command.AddAlias;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Deals with making sense of the user command.
 * */
public class Parser {

    /**
     * Parses given user's input and instantiate to Command object.
     *
     * @param fullcommand String input from user
     * @return Command object
     */
    public static Command parse(String fullcommand) throws DukeException {
        try {
            String subcommand = fullcommand.split(" ")[0];
            String command = fullcommand.replaceFirst(subcommand, "").trim();
            String type;
            if (Alias.aliases.containsKey(subcommand)) {
                type = Alias.aliases.get(subcommand);
            } else {
                throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }

            switch (type.toLowerCase()) {
            case "list":
                return new ListCommand();
            case "done":
                return new UpdateCommand(command);
            case "delete":
                return new DeleteCommand(command);
            case "find":
                return new FindCommand(command);
            case "todo":
                return new AddCommand("T", command);
            case "deadline":
                return new AddCommand("D", command);
            case "event":
                return new AddCommand("E", command);
            case "exit":
                return new ExitCommand();
            case "addalias":
                return new AddAlias(command);
            case "listalias":
                return new ListAlias();
            default:
                throw new AssertionError("INVALID PARSE: " + fullcommand);
            }
        } catch (DukeException e) {
            throw new DukeException(e.getMessage());
        }
    }

    /**
     * Checks if user input for task command is valid.
     *
     * @param type Char of task type
     * @param command String User input
     */
    public static void checkTask(String type, String command) throws DukeException {
        if (type.equals("T")) {
            if (command.isEmpty()) {
                throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
            }
        } else if (type.equals("D")) {
            String[] arr = command.split("/by");
            if (arr.length < 2) {
                throw new DukeException("OOPS!!! Deadline must be followed by /by.");
            } else if (arr[0].trim().isEmpty()) {
                throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
            } else if (arr[1].trim().isEmpty()) {
                throw new DukeException("OOPS!!! The deadline must have a specified date/time.");
            }
        } else if (type.equals("E")) {
            String[] arr = command.split("/at");
            if (arr.length < 2) {
                throw new DukeException("OOPS!!! Event must be followed by /at.");
            } else if (arr[0].trim().isEmpty()) {
                throw new DukeException("OOPS!!! The description of a event cannot be empty.");
            } else if (arr[1].trim().isEmpty()) {
                throw new DukeException("OOPS!!! The event must have a specified date/time.");
            }
        } else {
            throw new AssertionError("invalid task type: " + type);
        }
    }

    /**
     * Checks if command is a valid number.
     *
     * @param command String User input
     * @param listSize int size of TaskList
     * @throws DukeException if command is not a number or ArrayIndexOutOfBound in TaskList
     */
    public static boolean isValidNumber(String command, int listSize) throws DukeException {
        try {
            if (command.isEmpty()) {
                throw new DukeException("OOPS!!! Command cannot be empty! Please try again!");
            }

            if (!command.matches("\\d+")) {
                throw new DukeException("OOPS!!! Task id has to be numeric type!");
            }

            int pos = Integer.parseInt(command);
            if (pos > listSize || pos <= 0) {
                throw new DukeException("OOPS!!! Task id not within range of total number of tasks!");
            }

        } catch (DukeException e) {
            throw new DukeException(e.getMessage());
        }
        return true;
    }

    /**
     * Formats date and time from String.
     *
     * @param input Converts snippet of user input to DateTime format
     * @return String of formatted DateTime
     * @throws DukeException if DateTime format != "dd/mm/yyyy HHmm"
     */
    public static String datetimeformat(String input) throws DukeException {
        try {
            System.out.println(input);
            //convert to ISO_LOCAL_DATE_TIME
            LocalDateTime datetime = LocalDateTime.parse(input, DateTimeFormatter.ofPattern("d/MM/yyyy HHmm"));
            DateTimeFormatter format = DateTimeFormatter.ofPattern(" MMMM yyyy, h.mma");
            return getSuffix(datetime.getDayOfMonth()).concat(datetime.format(format));
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid date format: dd/mm/yyyy HHmm only!");
        }
    }

    /**
     * Gets day suffix for date formatter.
     *
     * @param day numeric value of the day
     * @return String day suffix
     */
    public static String getSuffix(int day) {
        switch (day) {
        case 1:
        case 21:
        case 31:
            return day + "st";
        case 2:
        case 22:
            return day + "nd";
        case 3:
        case 23:
            return day + "rd";
        default:
            return day + "th";
        }
    }

}
