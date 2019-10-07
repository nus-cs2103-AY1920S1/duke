package duke.core;

import duke.command.Command;
import duke.command.AddCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.UpdateCommand;

import duke.task.Task;
import duke.task.NormalTask;
import duke.task.Deadline;
import duke.task.Event;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Represents a <code>Parser</code> that parses user input into a specific 
 * type of <code>Command</code>. The <code>Parser</code> also parses tasks
 * related to the command, and checks for illegal instructions.
 */
public class Parser {

    /**
     * Creates a string from a sub array of a string array.
     *
     * @param words The string array to be processed.
     * @param start The starting position in the string array.
     * @param end The ending position in the string array.
     * @return A string consisting of all the words from the starting position
     *          to the ending position, separated by spaces.
     */
    private static String subString(String[] words, int start, int end) {
        StringBuilder sb = new StringBuilder();
        IntStream.rangeClosed(start, end - 1).forEach(i -> sb.append(words[i] + " "));
        return sb.toString().trim();
    }

    /**
     * Finds the index of a given string in a string array.
     *
     * @param words The string array to be inspected.
     * @param s The string to be searched.
     * @return The index of the given string in the string array. Returns -1 if
     *          the given string does not occur in the array.
     */
    private static int findIdx(String[] words, String s) {
        return Stream.of(words).collect(Collectors.toList()).indexOf(s);
    }

    /**
     * Formats a given date and time string into a more readable format. Applicable to deadlines.
     *
     * @param input The date and time string input by the user, in the form "d/MM/yyyy HHmm".
     * @return A better formatted string that clearly shows day of the week, abbreviation of the month, and AM/PM.
     * @throws DukeException If the given string cannot be parsed into <code>LocalDateTime</code>.
     */
    public static String formatDateTimeForDeadline(String input) throws DukeException {
        try {
            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
            LocalDateTime dueTime = LocalDateTime.parse(input, inputFormatter);
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("EEE, d MMM yyyy, h:mma");
            return outputFormatter.format(dueTime);
        } catch (DateTimeParseException e) {
            throw new DukeException("Input date and time is not in d/MM/yyyy HHmm format (24-hour clock) :-(");
        }
    }

    /**
     * Formats a given date and time string into a more readable format. Applicable to events.
     *
     * @param input The date and time string input by the user, in the form "d/MM/yyyy HHmm-HHmm".
     * @return A better formatted string that clearly shows day of the week, abbreviation of the month, and AM/PM.
     * @throws DukeException If the given string cannot be parsed into <code>LocalDateTime</code>.
     */
    public static String formatDateTimeForEvent(String input) throws DukeException {
        String[] words = input.split("-");
        if (words.length < 2) {
            throw new DukeException("Input date and time is not in d/MM/yyyy HHmm-HHmm format (24-hour clock) :-(");
        }
        try {
            String startTimeString = words[0];
            String formattedStartTime = formatDateTimeForDeadline(startTimeString);

            String endTimeString = words[1];
            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("HHmm");
            LocalTime endTime = LocalTime.parse(endTimeString, inputFormatter);
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("h:mma");
            String formattedEndTime = outputFormatter.format(endTime);

            return formattedStartTime + " to " + formattedEndTime;

        } catch (DateTimeParseException e) {
            throw new DukeException("Input date and time is not in d/MM/yyyy HHmm-HHmm format (24-hour clock) :-(");
        }
    }

    /**
     * Parses a <code>Task</code> from a string array that represents user instruction.
     *
     * @param words The string array to be parsed.
     * @return The <code>Task</code> that is parsed from the string array.
     * @throws DukeException If an exception occurs during parsing.
     */
    private static Task parseTaskFromInstruction(String[] words) throws DukeException {
        if (words[0].equals("todo")) {
            return new NormalTask(subString(words, 1, words.length));
        } else if (words[0].equals("deadline")) {
            int i = findIdx(words, "/by");
            String description = subString(words, 1, i);
            String dueTime = subString(words, i + 1, words.length);
            String formattedDueTime = formatDateTimeForDeadline(dueTime);
            return new Deadline(description, formattedDueTime);
        } else {
            assert words[0].equals("event") : "Instruction type should be event";
            int i = findIdx(words, "/at");
            String description = subString(words, 1, i);
            String occurTime = subString(words, i + 1, words.length);
            String formattedOccurTime = formatDateTimeForEvent(occurTime);
            return new Event(description, formattedOccurTime);
        }
    }

    /**
     * Parses a <code>Task</code> from a string array that represents one line from the locally stored file.
     *
     * @param words The string array to be parsed.
     * @return The <code>Task</code> that is parsed from the string array.
     * @throws DukeException If an exception occurs during parsing.
     */
    public static Task parseTaskFromFile(String[] words) throws DukeException {
        Task t;
        switch (words[0]) {
        case "T":
            t = new NormalTask(words[2]);
            break;
        case "D":
            t = new Deadline(words[2], words[3]);
            break;
        case "E":
            t = new Event(words[2], words[3]);
            break;
        default:
            throw new DukeException("Unable to parse task.");
        }
        boolean isDone = words[1].equals("O");
        if (isDone) {
            t.markAsDone();
        }
        return t;
    }

    /**
     * Checks if an inputted task id is valid (i.e. an integer larger than or equal to 1).
     * @param s The string that represents the task id inputted by user.
     * @return A boolean that indicates whether this task id is valid.
     */
    private static boolean isValidTaskId(String s) {
        try {
            int taskId = Integer.parseInt(s);
            return taskId >= 1;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Checks for invalid user input and throws exceptions accordingly.
     *
     * @param words The string array to be checked.
     * @throws DukeException If the inputted instruction is invalid.
     */
    private static void checkInvalidInstruction(String[] words) throws DukeException {
        String fw = words[0];
        boolean isDoneCommand = fw.equals("done");
        boolean isDeleteCommand = fw.equals("delete");
        boolean isUpdateCommand = fw.equals("update");

        if ((isDoneCommand || isDeleteCommand || isUpdateCommand)) {
            if (words.length < 2) {
                throw new DukeException("OOPS!!! The task number of the \"" + fw
                        + "\" command cannot be empty.");
            }
            if (!isValidTaskId(words[1])) {
                throw new DukeException("OOPS!!! The task does not exist :-(");
            }
        }
        if (isUpdateCommand && words.length < 4) {
            throw new DukeException("OOPS!!! The \"update\" command is missing further details. "
                    + "It should have 4 components.");
        }
        if (isUpdateCommand && !(words[2].equals("description") || words[2].equals("time"))) {
            throw new DukeException("OOPS!!! The attribute of an \"update\" command can only be"
                    + " \"description\" or \"time\".");
        }

        boolean isDeadlineCommand = fw.equals("deadline");
        boolean isEventCommand = fw.equals("event");

        if (isDeadlineCommand && findIdx(words, "/by") == -1) {
            throw new DukeException("OOPS!!! The time of a deadline cannot be empty.");
        }
        if (isEventCommand && findIdx(words, "/at") == -1) {
            throw new DukeException("OOPS!!! The time of an event cannot be empty.");
        }
    }

    /**
     * Parses a line of user instruction into respective <code>Command</code> 
     * types.
     *
     * @param instruction The string that represents user input.
     * @return A <code>Command</code> that represents the specific type of
     *          command the user gives.
     * @throws DukeException If the inputted instruction is invalid.
     */
    public static Command parse(String instruction) throws DukeException {
        String[] words = instruction.split(" ");
        checkInvalidInstruction(words);
        String fw = words[0];
        switch (fw) {
        case "bye":
            return new ExitCommand();
        case "done":
            return new DoneCommand(Integer.parseInt(words[1]));
        case "delete":
            return new DeleteCommand(Integer.parseInt(words[1]));
        case "list":
            return new ListCommand();
        case "find":
            if (words.length < 2) {
                throw new DukeException("OOPS!!! The keyword of a \"find\" command cannot be empty.");
            }
            return new FindCommand(instruction.substring(5));
        case "update":
            String newValue = subString(words, 3, words.length);
            return new UpdateCommand(Integer.parseInt(words[1]), words[2], newValue);
        case "deadline": case "event": case "todo":
            if (words.length < 2) {
                throw new DukeException("OOPS!!! The description of a task cannot be empty.");
            }
            Task t = parseTaskFromInstruction(words);
            return new AddCommand(t);
        default:
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means...\n"
                    + "The first word of your instruction seems to be an invalid command :-(");
        }
    }
}

