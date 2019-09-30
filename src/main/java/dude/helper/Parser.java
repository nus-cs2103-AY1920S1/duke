package dude.helper;

import dude.DudeException;
import dude.command.AddCommand;
import dude.command.Command;
import dude.command.DeleteCommand;
import dude.command.DoneCommand;
import dude.command.ExitCommand;
import dude.command.FindCommand;
import dude.command.ListCommand;
import dude.command.UpdateCommand;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;

/**
 * Processes user input into Commands.
 */
public class Parser {
    public static String filePath;
    public static DateTimeFormatter dateFormats = new DateTimeFormatterBuilder()
            .appendOptional(DateTimeFormatter.ofPattern("dd/M/yyyy HH:mm"))
            .appendOptional(DateTimeFormatter.ofPattern("dd-M-yyyy HH:mm"))
            .appendOptional(DateTimeFormatter.ofPattern("dd.M.yyyy HH:mm"))
            .appendOptional(DateTimeFormatter.ofPattern("dd/M/yyyy hh:mm"))
            .appendOptional(DateTimeFormatter.ofPattern("dd-M-yyyy hh:mm"))
            .appendOptional(DateTimeFormatter.ofPattern("dd.M.yyyy hh:mm"))
            .appendOptional(DateTimeFormatter.ofPattern("dd/M/yyyy h a"))
            .appendOptional(DateTimeFormatter.ofPattern("dd-M-yyyy h a"))
            .appendOptional(DateTimeFormatter.ofPattern("dd.M.yyyy h a"))
            .appendOptional(DateTimeFormatter.ofPattern("dd/M/yyyy hhmm a"))
            .appendOptional(DateTimeFormatter.ofPattern("dd-M-yyyy hhmm a"))
            .appendOptional(DateTimeFormatter.ofPattern("dd.M.yyyy hhmm a"))
            .appendOptional(DateTimeFormatter.ofPattern("dd/M/yyyy HHmm"))
            .appendOptional(DateTimeFormatter.ofPattern("dd-M-yyyy HHmm"))
            .appendOptional(DateTimeFormatter.ofPattern("dd.M.yyyy HHmm"))
            .appendOptional(DateTimeFormatter.ofPattern("dd/M/yyyy Hmm"))
            .appendOptional(DateTimeFormatter.ofPattern("dd-M-yyyy Hmm"))
            .appendOptional(DateTimeFormatter.ofPattern("dd.M.yyyy Hmm"))
            .appendOptional(DateTimeFormatter.ofPattern("dd/M/yyyy hhmm"))
            .appendOptional(DateTimeFormatter.ofPattern("dd-M-yyyy hhmm"))
            .appendOptional(DateTimeFormatter.ofPattern("dd.M.yyyy hhmm"))
            .appendOptional(DateTimeFormatter.ofPattern("dd MMM yyyy HHmm"))
            .appendOptional(DateTimeFormatter.ofPattern("dd MMMM yyyy HHmm"))
            .appendOptional(DateTimeFormatter.ofPattern("dd/M/yyyy"))
            .appendOptional(DateTimeFormatter.ofPattern("dd-M-yyyy"))
            .appendOptional(DateTimeFormatter.ofPattern("dd.M.yyyy"))
            .appendOptional(DateTimeFormatter.ofPattern("dd MMM yyyy"))
            .appendOptional(DateTimeFormatter.ofPattern("dd MMMM yyyy"))
            .toFormatter();

    public static DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mm a");

    /**
     * Converts user input into their relevant Command subclasses for processing.
     *
     * @param userInput Input from the user.
     * @return Command object to process input.
     * @throws DudeException If first word of input is not "list", "done", "todo", "deadline", "event", "delete", "bye".
     */
    public static Command parse(String userInput) throws DudeException {
        if ("bye".equals(userInput)) {
            return new ExitCommand(filePath, null);
        }
        String[] inputSplit = userInput.split(" ");
        switch (inputSplit[0]) {
        case "list":
            return new ListCommand();
        case "done":
            return new DoneCommand(inputSplit);
        case "todo":
            return new AddCommand("todo", userInput, inputSplit, filePath);
        case "deadline":
            return new AddCommand("deadline", userInput, inputSplit, filePath);
        case "event":
            return new AddCommand("event", userInput, inputSplit, filePath);
        case "clone":
            return new AddCommand("clone", userInput, inputSplit, filePath);
        case "delete":
            return new DeleteCommand(inputSplit);
        case "find":
            return new FindCommand(inputSplit, userInput.replaceFirst("find ", ""));
        case "update":
            return new UpdateCommand(filePath, userInput, inputSplit);
        default:
            // Exception if invalid instruction
            throw new DudeException(":( OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Converts String of certain formats to Date object.
     *
     * @param dateString  String to be converted to Date.
     * @param dateFormats DateTimeFormatter specifying format patterns to parse input String.
     * @return Date converted from String.
     */
    public static LocalDateTime convertToDate(String dateString, DateTimeFormatter dateFormats) {
        try {
            return LocalDateTime.parse(dateString, dateFormats);
        } catch (DateTimeParseException de) {
            return null;
        }
    }

    public static void setFilePath(String filePath) {
        Parser.filePath = filePath;
    }
}
