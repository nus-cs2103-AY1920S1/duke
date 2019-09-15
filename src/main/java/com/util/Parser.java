package com.util;

import com.commands.*;
import com.exceptions.DukeException;
import com.exceptions.parser.*;

import java.awt.font.NumericShaper;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

/**
 * Makes sense of the user input.
 * Creates the appropriate Command to be executed later.
 */
public class Parser {

    // Constructor
    public Parser() {
    }

    public Command parse(String userInput) throws DukeException {
        // Split input into individual words
        ArrayList<String> fullUserInputArr = splitStrIntoArr(userInput.trim(), " ");
        Command currCommand;
        // Identify command by first word
        String firstWord = fullUserInputArr.get(0);
        switch (firstWord) {
        case "bye":
            currCommand = new ExitCommand();
            break;
        case "list":
            currCommand = new ListCommand();
            break;
        case "todo":
            currCommand = parseToDoCommand(fullUserInputArr);
            break;
        case "find":
            currCommand = parseFindCommand(fullUserInputArr);
            break;
        case "deadline":
            currCommand = parseDeadlineCommand(fullUserInputArr);
            break;
        case "event":
            currCommand = parseEventCommand(fullUserInputArr);
            break;
        case "done":
            currCommand = parseDoneCommand(fullUserInputArr);
            break;
        case "delete":
            currCommand = parseDeleteCommand(fullUserInputArr);
            break;
        default:
            throw new UnknownCommandException();
        }
        return currCommand;
    }

    private Command parseToDoCommand(ArrayList<String> fullUserInputArr) throws DukeException {
        int numInputWords = fullUserInputArr.size();
        // Empty task description, just "todo"
        if (numInputWords == 1) {
            throw new EmptyDescriptionException("todo");
        }
        // Splice back the description
        String taskDescription = joinArrIntoStr(fullUserInputArr.subList(1, numInputWords));
        Command newCommand = new ToDoCommand(taskDescription);
        return newCommand;
    }

    /**
     * Handles user input of commands with subcommands (e.g. /by, /at).
     * @param command Command word (e.g. deadline, event)
     * @param subCommand String preceding sub-description, starts with backslash (e.g. /by, /at)
     * @param fullUserInputArr ArrayList of user input split into words
     * @return Corresponding command
     * @throws DukeException
     */
    private Command parseSubCommand(String command, String subCommand, ArrayList<String> fullUserInputArr) throws DukeException{
        int numInputWords = fullUserInputArr.size();
        boolean hasOnlyCommandWord = numInputWords == 1;
        // Empty description (e.g. just "deadline", "event /at")
        if (hasOnlyCommandWord || fullUserInputArr.get(1).equalsIgnoreCase(subCommand)) {
            throw new EmptyDescriptionException(command);
        }

        // Index of first occurrence of subcommand
        int firstSubCommandIdx = fullUserInputArr.indexOf(subCommand);
        int lastSubCommandIdx = fullUserInputArr.indexOf(subCommand);

        boolean hasSubCommand = firstSubCommandIdx != -1;
        boolean hasMultipleSubCommands = firstSubCommandIdx != lastSubCommandIdx;
        // No "/by","/at" or multiple "/by"s, "/at"s provided
        if (!hasSubCommand || hasMultipleSubCommands) {
            throw new IncorrectInfoInputException(subCommand);
        }
        boolean hasSubCommandDescription = firstSubCommandIdx != fullUserInputArr.size()-1;
        // No description of "/by" or "/at"
        if (!hasSubCommandDescription) {
            throw new EmptyDescriptionException(subCommand);
        }

        String taskDescription = joinArrIntoStr(fullUserInputArr.subList(1, firstSubCommandIdx));
        String subCommandDescription = joinArrIntoStr(fullUserInputArr.subList(firstSubCommandIdx+1, numInputWords));
        Command newCommand = new SubCommand(command, taskDescription, subCommand, subCommandDescription);
        return newCommand;
    }

    private Command parseDeadlineCommand(ArrayList<String> fullUserInputArr) throws DukeException {
        return parseSubCommand("deadline", "/by", fullUserInputArr);
    }

    private Command parseEventCommand(ArrayList<String> fullUserInputArr) throws DukeException {
        return parseSubCommand("event", "/at", fullUserInputArr);
    }

    private Command parseFindCommand(ArrayList<String> fullUserInputArr) throws DukeException {
        int numInputWords = fullUserInputArr.size();
        // Empty description, just "find" with no keyword
        if (numInputWords == 1) {
            throw new EmptyDescriptionException("find");
        }
        ArrayList<String> findKeywords = convertToArrayList(fullUserInputArr.subList(1, numInputWords));
        Command newCommand = new FindCommand(findKeywords);
        return newCommand;
    }

    private Command parseDoneCommand(ArrayList<String> fullUserInputArr) throws DukeException {
        int numInputWords = fullUserInputArr.size();
        // Empty description, just "done" with no keyword
        if (numInputWords == 1) {
            throw new IncorrectInfoInputException(StaticStrings.NO_DONE_IDX_PROVIDED);
        }
        // More than one input after "done"
        if (numInputWords > 2) {
            throw new IncorrectInfoInputException(StaticStrings.TOO_MANY_DONE_INPUT);
        }
        try {
            // Check if integer is provided, not characters
            int doneIdx = Integer.parseInt(fullUserInputArr.get(1));
            Command newCommand = new DoneCommand(doneIdx);
            return newCommand;
        } catch (NumberFormatException e) {
            throw new DukeException(StaticStrings.NON_INT_PROVIDED_DONE);
        }
    }

    private Command parseDeleteCommand(ArrayList<String> fullUserInputArr) throws DukeException {
        int numInputWords = fullUserInputArr.size();
        // Empty description, just "delete" with no keyword
        if (numInputWords == 1) {
            throw new DukeException(StaticStrings.NO_DELETE_IDX_PROVIDED);
        }
        // More than one input after "delete"
        if (numInputWords > 2) {
            throw new DukeException(StaticStrings.TOO_MANY_DELETE_INPUT);
        }
        try {
            // Check if integer is provided, not characters
            int deleteIdx = Integer.parseInt(fullUserInputArr.get(1));
            Command newCommand = new DeleteCommand(deleteIdx);
            return newCommand;
        } catch (NumberFormatException e) {
            throw new DukeException(StaticStrings.NON_INT_PROVIDED_DELETE);
        }
    }

    ////////////////////
    // HELPER METHODS //
    ///////////////////

    private ArrayList<String> convertToArrayList(String[] arr) {
        return new ArrayList<String>(Arrays.asList(arr));
    }
    private ArrayList<String> convertToArrayList(List<String> arr) {
        return new ArrayList<String>(arr);
    }

    /**
     * Splits a string by given regex pattern.
     * E.g. "A B C" to [A, B, C] with pattern " "
     * @param input String or sentence
     * @param pattern Regex expression to separate string by
     * @return ArrayList where each item is a substring
     */
    private ArrayList<String> splitStrIntoArr(String input, String pattern) {
        ArrayList<String> result = convertToArrayList(input.split(pattern));
        return result;
    }

    private String joinArrIntoStr(List<String> arr) {
        String result = String.join(" ", arr);
        return result;
    }

}
