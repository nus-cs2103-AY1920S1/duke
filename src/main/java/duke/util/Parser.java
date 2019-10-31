package duke.util;

import duke.command.Command;
import duke.command.CommandFind;
import duke.command.CommandHelp;
import duke.command.CommandHistory;
import duke.command.CommandList;
import duke.command.CommandMark;
import duke.command.CommandDelete;
import duke.command.CommandAdd;
import duke.command.CommandExit;
import duke.command.CommandRedo;
import duke.command.CommandUndo;

import java.time.LocalDateTime;

/**
 * Deals with making sense of user input.
 */
public class Parser {
    /**
     * Parses user input into a Command object.
     * @param fullCommand The full user input.
     * @return A Command object.
     * @throws DukeException If the user input is an invalid or unknown command.
     */
    public static Command parseCommand(String fullCommand) throws DukeException {
        String[] commandWords = fullCommand.split(" ");
        switch (commandWords[0]) {
        case "list":
            return new CommandList();
        case "done":
            try {
                return new CommandMark(Integer.parseInt(commandWords[1]));
            } catch (NumberFormatException e) {
                throw new DukeException("Invalid task number.");
            }
        case "delete":
            try {
                return new CommandDelete(Integer.parseInt(commandWords[1]));
            } catch (NumberFormatException e) {
                throw new DukeException("Invalid task number.");
            }
        case "todo":
        case "deadline":
        case "event":
            try {
                String taskType = fullCommand.split(" ")[0];
                String taskDescription = fullCommand.split(" ", 2)[1];
                return new CommandAdd(taskType, taskDescription);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("To add tasks, please use the format: type description\n"
                        + "e.g. todo read book"
                );
            }
        case "find":
            try {
                String query = fullCommand.split(" ", 2)[1];
                return new CommandFind(query);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("Invalid search term.");
            }
        case "help":
            return new CommandHelp();
        case "undo":
            return new CommandUndo();
        case "redo":
            return new CommandRedo();
        case "history":
            return new CommandHistory();
        case "bye":
            return new CommandExit();
        default:
            String unknownCommand = commandWords[0].length() > 20
                    ? commandWords[0].substring(0, 20) + "..."
                    : commandWords[0];
            throw new DukeException("I'm sorry, I don't know what '" + unknownCommand + "' means :(\n\n"
                    + "Type 'help' to get a list of commands I can read.");
        }
    }

    /**
     * Parses a given date and time into a LocalDateTime object.
     * @param dateTime A date and time string in the format: dd/MM/yyyy, hh:mmPM
     * @return A LocalDateTime object representing the given date and time.
     * @throws DukeException If the date and time are provided do not match the required format.
     */
    public static LocalDateTime parseDateTimeString(String dateTime) throws DukeException {
        // ensure that dateTime string is in the format: "12/02/2019, 6:05pm"
        try {
            // get day of month, month, and year
            String[] dateArray = dateTime.split(", ")[0].split("/");
            int dayOfMonth = Integer.parseInt(dateArray[0]);
            int month = Integer.parseInt(dateArray[1]);
            int year = Integer.parseInt(dateArray[2]);

            // get minute and hour
            String time = dateTime.split(", ")[1];
            int minute = Integer.parseInt(time.split(":")[1].substring(0, 2));
            int hour = Integer.parseInt(time.split(":")[0]);

            // check if given hour is valid
            if (hour <= 0) {
                throw new DukeException("Hour cannot be less than or equal to 0");
            }

            // convert hour to 24-hour format (0 - 23) based on given AM/PM
            boolean isPastNoon = time.substring(time.length() - 2).equalsIgnoreCase("pm");
            if (isPastNoon && hour != 12) {
                // convert to 24 hour format (except for 12pm)
                hour += 12;
            } else if (!isPastNoon && hour == 12) {
                // edge case: 12am to 12:59am
                hour = 0;
            }

            return LocalDateTime.of(year, month, dayOfMonth, hour, minute);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new DukeException(
                    "DateTime \"" + dateTime + "\" format incorrect.\n\n" + Ui.getDatetimeFormatMessage()
            );
        }
    }
}
