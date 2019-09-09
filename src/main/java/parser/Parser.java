package parser;

import command.Command;
import command.DeadlineCommand;
import command.DeleteCommand;
import command.DoneCommand;
import command.EventCommand;
import command.ExitCommand;
import command.FindCommand;
import command.ListCommand;
import command.ToDoCommand;
import exception.DukeException;

import java.util.Scanner;

/**
 * Deals with making sense of user command.
 */
public class Parser {
    /**
     * Parses user input into command for execution.
     *
     * @param userInput String of input of user
     * @return the command based on user input
     * @throws DukeException if user input is invalid
     */
    public Command parse(String userInput) throws DukeException {
        String[] separatedInputs = userInput.trim().split("\\s+");
        switch (separatedInputs[0].toLowerCase()) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "done":
            int doneIdx = checkInput(separatedInputs);
            return new DoneCommand(doneIdx);
        case "delete":
            int deleteIdx = checkInput(separatedInputs);
            return new DeleteCommand(deleteIdx);
        case "todo":
            String description = userInput.trim().substring(separatedInputs[0].length()).trim();
            if (description.isEmpty()) {
                throw new DukeException(DukeException.Error_Type.EMPTY_TODO);
            }
            return new ToDoCommand(description);
        case "deadline":
            if (!userInput.contains("/by")) {
                throw new DukeException(DukeException.Error_Type.EMPTY_DEADLINE_DATE);
            }
            String[] DeadlineDetails = getDetails(userInput, "/by", separatedInputs[0].length());
            return new DeadlineCommand(DeadlineDetails);
        case "event":
            if (!userInput.contains("/at")) {
                throw new DukeException(DukeException.Error_Type.EMPTY_EVENT_DATE);
            }
            String[] EventDetails = getDetails(userInput, "/at", separatedInputs[0].length());
            return new EventCommand(EventDetails);
        case "find":
            String keyword = userInput.trim().substring(separatedInputs[0].length()).trim();
            return new FindCommand(keyword);
        default:
            throw new DukeException(DukeException.Error_Type.GIBBERISH);
        }
    }

    /**
     * Checks if user input is valid for done and delete command.
     *
     * @param separatedInputs the input of users in String array separated by whitespace
     * @return the index of the task to be marked done or deleted
     * @throws DukeException the user input has entered invalid task ID
     */
    private int checkInput(String[] separatedInputs) throws DukeException {
        if (separatedInputs.length != 2) {
            throw new DukeException(DukeException.Error_Type.INVALID_NUMBER);
        }
        Scanner s = new Scanner(separatedInputs[1]);
        if (!s.hasNextInt()) {
            throw new DukeException(DukeException.Error_Type.INVALID_NUMBER);
        }
        return s.nextInt() - 1;
    }

    /**
     * Extracts description and date of event or deadline from user input.
     *
     * @param userInput the input of user
     * @param keyword the keyword that separates description and date
     * @param startIdx the starting index of description
     * @return String array containing description and date of event or deadline
     */
    private String[] getDetails(String userInput, String keyword, int startIdx) {
        return userInput.trim().substring(startIdx).trim().split(keyword);
    }
}
