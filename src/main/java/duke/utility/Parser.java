package duke.utility;

import duke.commands.Command;
import duke.commands.ListCommand;
import duke.commands.DeadlineCommand;
import duke.commands.DeleteCommand;
import duke.commands.DoneCommand;
import duke.commands.EventCommand;
import duke.commands.ExitCommand;
import duke.commands.FindCommand;
import duke.commands.TodoCommand;
import duke.exception.DukeException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Parser {

    private static SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy HHmm");

    /**
     * Method to parse user input into a command for Duke to execute.
     *
     * @param userInput Command the user typed into Duke.
     * @return Command object based on user input.
     * @throws DukeException if no matching command found.
     */
    public static Command parseCommand(String userInput) throws DukeException {
        String[] splittedInput = userInput.split(" ", 2);
        switch (splittedInput[0]) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "done":
            return new DoneCommand(splittedInput[1]);
        case "todo":
            return new TodoCommand(splittedInput[1]);
        case "deadline":
            return new DeadlineCommand(splittedInput[1]);
        case "event":
            return new EventCommand(splittedInput[1]);
        case "delete":
            return new DeleteCommand(splittedInput[1]);
        case "find":
            return new FindCommand(splittedInput[1]);
        default:
            throw new DukeException(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Method to format date from DD/MM/YYYY HHMM to text form.
     *
     * @param input Date and Time string in DD/MM/YYYY HHMM form.
     * @return Formatted string in desired format.
     * @throws DukeException if string to be formatted not in correct syntax.
     */
    public static String getFormattedDate(String input) throws DukeException {
        String parsedDate = "";
        try {
            dateFormatter.parse(input);
            Date date = new SimpleDateFormat("dd/MM/yyyy hhmm").parse(input);
            String day = new SimpleDateFormat("dd").format(date);
            String month = new SimpleDateFormat("MMMMMMMMMMMMMMM").format(date);
            String year = new SimpleDateFormat("yyyy").format(date);
            String time = new SimpleDateFormat("h:mm a").format(date).toLowerCase();
            String postfix;

            int correctedDay = Integer.parseInt(day);
            if (correctedDay >= 11 && correctedDay <= 13) {
                postfix = "th";
            } else if (correctedDay % 10 == 1) {
                postfix = "st";
            } else if (correctedDay % 10 == 2) {
                postfix = "nd";
            } else if (correctedDay % 10 == 3) {
                postfix = "rd";
            } else {
                postfix = "th";
            }

            parsedDate = correctedDay + postfix + " of " + month + " " + year + ", " + time;
        } catch (ParseException e) {
            throw new DukeException("Wrong date format! Please specify date correctly in DD/MM/YYYY HHMM format!");
        }
        return parsedDate;
    }


}