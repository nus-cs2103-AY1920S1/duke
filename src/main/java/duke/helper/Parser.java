package duke.helper;

import duke.DukeException;
import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Processes user input into Commands.
 */
public class Parser {
    public static String filePath;
    public static ArrayList<SimpleDateFormat> dateFormats = new ArrayList<>() {
        {
            add(new SimpleDateFormat("dd/M/yyyy HH:mm"));
            add(new SimpleDateFormat("dd-M-yyyy HH:mm"));
            add(new SimpleDateFormat("dd.M.yyyy HH:mm"));
            add(new SimpleDateFormat("dd/M/yyyy hh:mm"));
            add(new SimpleDateFormat("dd-M-yyyy hh:mm"));
            add(new SimpleDateFormat("dd.M.yyyy hh:mm"));
            add(new SimpleDateFormat("dd/M/yyyy h a"));
            add(new SimpleDateFormat("dd-M-yyyy h a"));
            add(new SimpleDateFormat("dd.M.yyyy h a"));
            add(new SimpleDateFormat("dd/M/yyyy hhmm a"));
            add(new SimpleDateFormat("dd-M-yyyy hhmm a"));
            add(new SimpleDateFormat("dd.M.yyyy hhmm a"));
            add(new SimpleDateFormat("dd/M/yyyy HHmm"));
            add(new SimpleDateFormat("dd-M-yyyy HHmm"));
            add(new SimpleDateFormat("dd.M.yyyy HHmm"));
            add(new SimpleDateFormat("dd/M/yyyy Hmm"));
            add(new SimpleDateFormat("dd-M-yyyy Hmm"));
            add(new SimpleDateFormat("dd.M.yyyy Hmm"));
            add(new SimpleDateFormat("dd/M/yyyy hhmm"));
            add(new SimpleDateFormat("dd-M-yyyy hhmm"));
            add(new SimpleDateFormat("dd.M.yyyy hhmm"));
            add(new SimpleDateFormat("dd MMM yyyy HHmm"));
            add(new SimpleDateFormat("dd MMMM yyyy HHmm"));
            add(new SimpleDateFormat("dd/M/yyyy"));
            add(new SimpleDateFormat("dd-M-yyyy"));
            add(new SimpleDateFormat("dd.M.yyyy"));
            add(new SimpleDateFormat("dd MMM yyyy"));
            add(new SimpleDateFormat("dd MMMM yyyy"));
        }
    };

    /**
     * Converts user input into their relevant Command subclasses for processing.
     *
     * @param userInput Input from the user.
     * @return Command object to process input.
     * @throws DukeException If first word of input is not "list", "done", "todo", "deadline", "event", "delete", "bye".
     */
    public static Command parse(String userInput) throws DukeException {
        if ("bye".equals(userInput)) {
            return new ExitCommand(filePath, null);
        }
        String[] inputSplit = userInput.split(" ");
        switch (inputSplit[0]) {
        case "list":
            return new ListCommand(filePath, null);
        case "done":
            return new DoneCommand(filePath, inputSplit);
        case "todo":
            return new AddCommand("todo", userInput, inputSplit, filePath);
        case "deadline":
            return new AddCommand("deadline", userInput, inputSplit, filePath);
        case "event":
            return new AddCommand("event", userInput, inputSplit, filePath);
        case "delete":
            return new DeleteCommand(filePath, inputSplit);
        case "find":
            return new FindCommand(filePath, null, userInput.replaceFirst("find ", ""));
        default:
            // Exception if invalid instruction
            Ui ui = new Ui();
            throw new DukeException(ui.separationLine
                    + "\n     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n"
                    + ui.separationLine + "\n");
        }
    }

    /**
     * Converts String of certain formats to Date object.
     *
     * @param dateString String to be converted to Date.
     * @param dateFormats List of valid SimpleDateFormat objects that correspond to convertible String formats.
     * @return Date converted from String.
     */
    public static Date convertToDate(String dateString, ArrayList<SimpleDateFormat> dateFormats) {
        Date date = null;
        for (SimpleDateFormat sdf : dateFormats) {
            try {
                sdf.setLenient(false);
                date = sdf.parse(dateString);
            } catch (ParseException pe) {
                // Continue checking for matching date format
            }
            if (date != null) {
                break;
            }
        }
        return date;
    }

    public static void setFilePath(String filePath) {
        Parser.filePath = filePath;
    }
}
