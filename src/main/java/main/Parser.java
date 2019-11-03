package main;

import command.AddCommand;
import command.ArchiveCommand;
import command.ClearAllTasksCommand;
import command.Command;
import command.DeleteCommand;
import command.DoneCommand;
import command.ExitCommand;
import command.FindCommand;
import command.HelpCommand;
import command.ListCommand;

/**
 * Deals with making sense of the user command.
 */
public class Parser {

    /**
     * Returns a Command object to parse the command.
     *
     * @param fullCommand The full user command received from the ui.
     * @return A command object to parse the command
     */
    public static Command parse(String fullCommand) throws DukeException {

        String[] arr = fullCommand.split(" ", 2);
        String cmd = arr[0].toLowerCase();

        switch (cmd) {
        case "help":
            if (arr.length == 2) {
                return new HelpCommand(arr[1].trim().toLowerCase());
            } else {
                return new HelpCommand();
            }
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "clear":
            return new ClearAllTasksCommand();
        case "done":
            try {
                return new DoneCommand(Integer.parseInt(arr[1].trim()));
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("Please enter a valid task ID to mark as complete!");
            } catch (NumberFormatException e) {
                throw new DukeException("Please enter a numerical value for the task ID");
            }
        case "delete":
            try {
                return new DeleteCommand(Integer.parseInt(arr[1].trim()));
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("Please enter a valid task ID to delete!");
            } catch (NumberFormatException e) {
                throw new DukeException("Please enter a numerical value for the task ID");
            }
        case "find":
            try {
                String keyword = arr[1].trim().toLowerCase();
                return new FindCommand(keyword);
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("Please enter a keyword to find");
            }
        case "todo":
        case "event":
        case "deadline":
            try {
                return new AddCommand(cmd, arr[1].trim());
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("The description of the command '" + cmd + "' cannot be empty!");
            }
        case "archive":
            try {
                return new ArchiveCommand(arr[1].trim());
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("Enter a command behind archive. "
                        + "Supported commands include archive all/today/done/taskID");
            }
        default:
            throw new DukeException("I'm sorry I do not understand what you mean. "
                    + "Please enter 'help' to see all supported commands.");
        }
    }
}
