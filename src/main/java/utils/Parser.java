package utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import command.AddCommand;
import command.Command;
import command.DeleteCommand;
import command.DoneCommand;
import command.ExitCommand;
import command.FindCommand;
import command.InvalidCommand;
import command.ListCommand;

/**
 * Parser object handles all input reading and prints the respective output.
 */
public class Parser {

    /**
     * A method to determine the suffix, given the date.
     *
     * @param dateTime a LocalDateTime
     * @return String representing "st", "nd", "rd" or "th"
     */
    private static String getSuffix(LocalDateTime dateTime) {

        int day = dateTime.getDayOfMonth();
        int remainder = day % 10;

        switch (remainder) {
        case 1:
            return "st";
        case 2:
            return "nd";
        case 3:
            return "rd";
        default:
            return "th";
        }
    }

    /**
     * A method to convert dates into the correct format of type: "1st November
     * 2019, 2.30pm".
     *
     * @param dateTimeString the unformatted date
     * @return String representing dates in the format: "1st November 2019, 2.30pm"
     */
    private static String getDate(String dateTimeString) throws DateTimeParseException {

        // Create formatter to recognise input pattern and convert to LocalDateTime
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, formatter);
        String suffix = getSuffix(dateTime) + " of";

        // Format LocalDateTime
        DateTimeFormatter wantedFormat = DateTimeFormatter.ofPattern(" d'" + suffix + "' MMMM yyyy, h.mm a");
        return wantedFormat.format(dateTime);
    }

    /**
     * Static method that parses strings into their respective commands.
     *
     * @param fullCommand The fullCommand read from input.
     * @return Command
     */
    public static Command parse(String fullCommand) {
        String[] arr = fullCommand.split(" ", 2);
        String keyCommand = arr[0].trim();

        try {
            switch (keyCommand) {
            case "list":
                return new ListCommand();
            case "bye":
                return new ExitCommand();
            case "find":
                String description = arr[1].trim();
                return new FindCommand(description);
            case "done": {
                int index = Integer.parseInt(arr[1].trim());
                return new DoneCommand(index);
            }
            case "delete": {
                int index = Integer.parseInt(arr[1].trim());
                return new DeleteCommand(index);
            }
            case "todo":

                if (arr.length != 2) {
                    return new InvalidCommand("Description for todo should not be empty!");
                } else {
                    String description = arr[1].trim();
                    return new AddCommand(keyCommand, description);
                }
            case "deadline": {

                String info = arr[1].trim();
                String[] wordArr = info.split("/by", 2);

                if (wordArr.length != 2) {
                    return new InvalidCommand("Deadlines should be followed with a /by.");
                }

                String formattedDate = getDate(wordArr[1].trim());
                return new AddCommand(keyCommand, wordArr[0].trim(), formattedDate);
            }
            case "event": {

                String info = arr[1].trim();
                String[] wordArr = info.split("/at", 2);

                if (wordArr.length != 2) {
                    return new InvalidCommand("Events should be followed with a /at.");
                }

                String formattedDate = getDate(wordArr[1].trim());
                return new AddCommand(keyCommand, wordArr[0].trim(), formattedDate);

            }
            default:
                return new InvalidCommand("Sorry! I don't understand what that means :(");
            }
        } catch (NumberFormatException e) {
            return new InvalidCommand("An integer should be followed by done / delete.");
        } catch (DateTimeParseException e) {
            return new InvalidCommand("Dates should be supplied in the format: dd/mm/yyyy hhmm.");
        }

    }

}