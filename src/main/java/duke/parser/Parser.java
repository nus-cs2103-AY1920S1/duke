package duke.parser;

import duke.command.AddCommand;
import duke.command.AddTagCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DeleteTagCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;

import duke.exception.DukeException;
import duke.exception.DukeIntFormatException;
import duke.exception.DukeEmptyDescriptionException;
import duke.exception.DukeMissingDateTimeException;
import duke.exception.DukeMissingKeywordException;
import duke.exception.DukeUnknownCommandException;

/**
 * Represents a class that takes in inputs and translates them into different commands.
 */
public class Parser {

    /**
     * Takes in a string input and convert the input into a command which performs a set of
     * instructions on the task list, ui and storage.
     *
     * @param input String that contains the input of the user.
     * @return A command that execute a set of instructions.
     * @throws DukeException Thrown when there is a Duke exception.
     */
    public static Command parse(String input) throws DukeException {
        String[] arguments = input.split(" ");
        int num;

        switch (arguments[0]) {
        case "bye":
            return handleExitCase();
        case "list":
            return handleListCase();
        case "done":
            //Fallthrough
        case "delete":
            num = parseInteger(arguments[0], arguments[1]);
            return handleDoneDeleteCase(arguments[0], num);
        case "tag":
            //Fallthrough
        case "deletetag":
            num = parseInteger(arguments[0], arguments[1]);
            String tagName = parseArrayToString(arguments, 2, arguments.length);
            return handleTagDeleteTagCase(arguments[0], num, tagName);
        case "deadline":
            //Fallthrough
        case "event":
            //Fallthrough
        case "todo":
            return handleAddTaskCommands(arguments);
        case "find":
            return handleFindCommand(arguments.length, arguments[1]);
        default:
            throw new DukeUnknownCommandException();
        }
    }

    private static int parseInteger(String command, String input) throws DukeIntFormatException {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new DukeIntFormatException(command);
        }
    }

    private static Command handleExitCase() {
        return new ExitCommand();
    }

    private static Command handleListCase() {
        return new ListCommand();
    }

    private static Command handleDoneDeleteCase(String command, int index) {
        if (command.equals("done")) {
            return new DoneCommand(index);
        } else if (command.equals("delete")) {
            return new DeleteCommand(index);
        } else {
            assert false : " done and delete command not found";
            return null;
        }
    }

    private static Command handleTagDeleteTagCase(String command, int index, String tagName) {
        if (command.equals("tag")) {
            return new AddTagCommand(index, tagName);
        } else if (command.equals("deletetag")) {
            return new DeleteTagCommand(index, tagName);
        } else {
            assert false : " tag and deletetag command not found";
            return null;
        }
    }

    private static Command handleAddTaskCommands(String[] arguments) throws DukeEmptyDescriptionException,
            DukeMissingDateTimeException {
        if (arguments.length == 1) {
            throw new DukeEmptyDescriptionException(arguments[0]);
        }

        int index = -1;

        if (arguments[0].equals("deadline")) {
            index = findIndexByToken(arguments, "/by");
            if (index == -1) {
                throw new DukeMissingDateTimeException(arguments[0]);
            }
        } else if (arguments[0].equals("event")) {
            index = findIndexByToken(arguments, "/at");
            if (index == -1) {
                throw new DukeMissingDateTimeException(arguments[0]);
            }
        }

        if (arguments[0].equals("todo")) {
            String description = parseArrayToString(arguments, 1, arguments.length);
            return new AddCommand(arguments[0], description, "00/00/0000 0000");
        } else {
            String description = parseArrayToString(arguments, 1, index);
            String dateTime = parseArrayToString(arguments, index + 1, arguments.length);
            return new AddCommand(arguments[0], description, dateTime);
        }
    }

    private static Command handleFindCommand(int lengthOfInput, String wordToFind) throws DukeMissingKeywordException {
        if (lengthOfInput == 1) {
            throw new DukeMissingKeywordException();
        }
        return new FindCommand(wordToFind);
    }


    //returns index of token if found, else returns -1
    //stops at first occurrence of token
    private static int findIndexByToken(String[] array, String token) {

        assert array != null : " String array is null";
        assert token != null : " Token is null";

        int index = -1;
        for (int i = 1; i < array.length; i++) {
            if (array[i].equals(token)) {
                index = i;
                break;
            }
        }

        return index;
    }

    // Combines words into a sentence
    private static String parseArrayToString(String[] array, int start, int end) {

        assert array != null : " String array cannot be null";
        assert start >= 0 : " Start cannot be less than 0";
        assert end >= 0 : " End cannot be less than 0";

        if (start >= end) {
            return "";
        }
        StringBuilder result = new StringBuilder(array[start]);
        for (int i = start + 1; i < end; i++) {
            result.append(" ").append(array[i]);
        }
        return result.toString();
    }
}
