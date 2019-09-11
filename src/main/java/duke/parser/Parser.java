package duke.parser;

import duke.exception.DukeException;
import duke.exception.DukeParserException;
import duke.exception.DukeTaskException;
import duke.task.Task;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskList;
import duke.task.Todo;
import duke.ui.Ui;

import java.text.ParseException;
import java.util.Optional;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A Parser class parses all the user input.
 */
public class Parser {
    private static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HHmm");

    /**
     * Parses the user input passed into duke and extracts the command keyword.
     *
     * @param input the user's input.
     * @return the command keyword.
     */
    public static String parseCommand(String input) {
        return input.split(" ", 2)[0];
    }

    /**
     * Parses the user input passed into duke and extract the description of the command.
     *
     * @param input the user's input.
     * @return the command's description.
     */
    public static Optional<String[]> parseDescription(String input) {
        String[] userInput = input.split(" ", 2);
        if (userInput.length == 1) {
            return Optional.empty();
        }
        String desc = userInput[1];
        if (desc.contains("/by")) {
            String[] keywords = desc.split(" /by ");
            return Optional.of(keywords);
        } else if (desc.contains("/at")) {
            String[] keywords = desc.split(" /at ");
            return Optional.of(keywords);
        } else {
            return Optional.of(new String[]{desc});
        }
    }

    /**
     * Parses the user's date input to return a Date object.
     *
     * @param input the user's date input when they specify the date of a task.
     * @return a Date object which date has been parsed into the desired format obtained from the formatter.
     * @throws DukeException if the user's input is not in the right format.
     */
    public static Date parseDate(String input) throws DukeParserException {
        try {
            return formatter.parse(input);
        } catch (ParseException e) {
            throw new DukeParserException("Please key a date in the format dd/MM/yyyy HHHH.");
        }
    }
}
