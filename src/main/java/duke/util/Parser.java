package duke.util;

import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ListCommand;
import duke.command.AddCommand;
import duke.command.FindCommand;
import duke.command.ExitCommand;
import duke.command.SortCommand;
import duke.exception.DukeException;

/**
 * Represents a parser object to process commands.
 */
public class Parser {

    /**
     * Parses user commands.
     *
     * @param fullCommand command from user input
     * @return a Command
     * @throws DukeException if the command is invalid
     */
    public static Command parse(String fullCommand) throws DukeException {
        assert !fullCommand.isEmpty() : "Please enter something!";
        String[] commandWords = fullCommand.split(" ");
        String action = commandWords[0];
        try {
            switch (action) {
            case "done":
                return processDone(commandWords);
            case "delete":
                return processDelete(commandWords);
            case "list":
                return new ListCommand();
            case "bye":
                return new ExitCommand();
            case "event":
                return processEvent(fullCommand, action);
            case "todo":
                return processTodo(fullCommand);
            case "deadline":
                return processDeadline(fullCommand, action);
            case "find":
                String taskToBeFound = fullCommand.substring(5);
                return new FindCommand(taskToBeFound);
            case "sort":
                return new SortCommand();
            default:
                throw new DukeException(Ui.TAB + "☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException(Ui.TAB + "Please enter the description!");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException(Ui.TAB + "Please enter the time/date!!");
        } catch (Exception e) {
            throw new DukeException(Ui.TAB + e.getMessage());
        }
    }

    private static Command processEvent(String fullCommand, String action) throws DukeException {
        assert fullCommand.length() > 6 : "incomplete command";
        String[] eventComponents = fullCommand.substring(6).split(" /at ");
        String eventDescription = eventComponents[0];
        String eventTime = eventComponents[1];
        return new AddCommand(action, eventDescription, eventTime);
    }

    private static Command processDeadline(String fullCommand, String action) throws DukeException {
        assert fullCommand.length() > 9 : "incomplete command";
        String[] deadlineComponents = fullCommand.substring(9).split(" /by ");
        String deadlineDescription = deadlineComponents[0];
        String deadlineTime = deadlineComponents[1];
        return new AddCommand(action, deadlineDescription, deadlineTime);
    }

    private static Command processTodo(String fullCommand) {
        assert fullCommand.length() > 5 : "incomplete command";
        String todoDescription = fullCommand.substring(5);
        return new AddCommand(todoDescription);
    }

    private static Command processDone(String[] commandWords) throws DukeException {
        try {
            int doneItemNo = Integer.parseInt(commandWords[1]);
            return new DoneCommand(doneItemNo);
        } catch (NumberFormatException e) {
            throw new DukeException(e.getMessage());
        }
    }

    private static Command processDelete(String[] commandWords) throws DukeException {
        try {
            int deletedItemNo = Integer.parseInt(commandWords[1]);
            return new DeleteCommand(deletedItemNo);
        } catch (NumberFormatException e) {
            throw new DukeException((e.getMessage()));
        }
    }
}
