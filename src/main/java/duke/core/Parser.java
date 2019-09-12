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
import duke.task.ToDo;
import duke.task.Deadline;
import duke.task.Event;

import java.time.LocalDateTime;
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

    private static String parseDateTime(String input) throws DukeException {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
        try {
            LocalDateTime dateTime = LocalDateTime.parse(input, inputFormatter);
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("EEE, d MMM yyyy, h:mma");
            return outputFormatter.format(dateTime);
        } catch (DateTimeParseException e) {
            throw new DukeException("Input date and time is not in dd/MM/yyyy HHmm format (24-hour clock) :-(");
        }
    }

    /**
     * Parses a <code>Task</code> from a string array.
     *
     * @param words The string array to be parsed.
     * @return The <code>Task</code> that is parsed from the string array.
     */
    private static Task parseTask(String[] words) throws DukeException {
        if (words[0].equals("todo")) {
            return new ToDo(subString(words, 1, words.length));
        } else if (words[0].equals("deadline")) {
            int i = findIdx(words, "/by");
            String description = subString(words, 1, i);
            String by = subString(words, i + 1, words.length);
            String dateTimeString = parseDateTime(by);
            return new Deadline(description, dateTimeString);
        } else {
            assert words[0].equals("event") : "Instruction type should be event";
            int i = findIdx(words, "/at");
            String description = subString(words, 1, i);
            String at = subString(words, i + 1, words.length);
            String dateTimeString = parseDateTime(at);
            return new Event(description, dateTimeString);
        }
    }

    private static boolean isValidTaskId(String s) {
        try {
            int taskId = Integer.parseInt(s);
            return taskId >= 1;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Checks for illegal user input and throws exceptions accordingly.
     *
     * @param words The string array to be checked.
     */
    private static void checkIllegalInstruction(String[] words) throws DukeException {
        String fw = words[0];
        boolean isDoneCommand = fw.equals("done");
        boolean isTodoCommand = fw.equals("todo");
        boolean isDeadlineCommand = fw.equals("deadline");
        boolean isEventCommand = fw.equals("event");
        boolean isDeleteCommand = fw.equals("delete");
        boolean isListCommand = fw.equals("list");
        boolean isExitCommand = fw.equals("bye");
        boolean isFindCommand = fw.equals("find");
        boolean isUpdateCommand = fw.equals("update");

        boolean isValidCommand = isDoneCommand || isTodoCommand || isDeadlineCommand || isEventCommand
                                || isDeleteCommand || isListCommand || isExitCommand || isFindCommand
                                || isUpdateCommand;
        boolean isAddCommand = isTodoCommand || isDeadlineCommand || isEventCommand;
        boolean isMissingDetail = words.length < 2;
        boolean DeadlineMissingTime = isDeadlineCommand && findIdx(words, "/by") == -1;
        boolean EventMissingTime = isEventCommand && findIdx(words, "/at") == -1;

        if (!isValidCommand) {
            throw new DukeException(" \u2639  OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        if (isAddCommand && isMissingDetail) {
            throw new DukeException(" \u2639  OOPS!!! The description of a " + fw + " cannot be empty.");
        }
        if (DeadlineMissingTime || EventMissingTime) {
            throw new DukeException(" \u2639  OOPS!!! The time of a(n) " + fw + " cannot be empty.");
        }
        if ((isDoneCommand || isDeleteCommand || isUpdateCommand) && isMissingDetail) {
            throw new DukeException(" \u2639  OOPS!!! The task number of a(n) " + fw + " command cannot be empty.");
        }
        if ((isDoneCommand || isDeleteCommand || isUpdateCommand) && !isValidTaskId(words[1])) {
            throw new DukeException(" \u2639  OOPS!!! The task number of the " + fw + " command is invalid.");
        }
        if (isUpdateCommand && words.length < 4) {
            throw new DukeException(" \u2639  OOPS!!! The update command is missing further details. "
                    + "It should have 4 components.");
        }
        if (isUpdateCommand && !(words[2].equals("description") || words[2].equals("time"))) {
            throw new DukeException(" \u2639  OOPS!!! The attribute of an update command can only be" +
                    " \"description\" or \"time\".");
        }
        if (isFindCommand && isMissingDetail) {
            throw new DukeException(" \u2639  OOPS!!! The keyword of a " + fw + " command cannot be empty.");
        }
    }

    /**
     * Parses a line of user instruction into respective <code>Command</code> 
     * types.
     *
     * @param s The string that represents user input.
     * @return A <code>Command</code> that represents the specific type of
     *          command the user gives.
     */
    public static Command parse(String s) throws DukeException {
        String[] words = s.split(" ");
        checkIllegalInstruction(words);
        String fw = words[0];
        assert fw.equals("bye") || fw.equals("done") || fw.equals("delete")
                || fw.equals("list") || fw.equals("find") || fw.equals("todo")
                || fw.equals("event") || fw.equals("deadline") || fw.equals("update")
                : "Invalid user input";
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
            return new FindCommand(words[1]);
        case "update":
            String newValue = subString(words, 3, words.length);
            if (words[2].equals("description")) {
                return new UpdateCommand(Integer.parseInt(words[1]), words[2], newValue);
            } else {
                assert words[2].equals("time") : "Invalid attribute type for an update command";
                return new UpdateCommand(Integer.parseInt(words[1]), words[2], parseDateTime(newValue));
            }
        default:
            assert fw.equals("todo") || fw.equals("event") || fw.equals("deadline")
                    : "First word should be todo / deadline / event";
            Task t = parseTask(words);
            return new AddCommand(t);
        }
    }
}

