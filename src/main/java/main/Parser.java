package main;

import command.*;
import command.Command;

/**
 * deals with making sense of the user command
 */
public class Parser {

    /**
     * Returns a Command object
     * @param fullCommand
     * @return
     */
    public static Command parse(String fullCommand) throws DukeException {

        String[] arr = fullCommand.split(" ", 2);
        String cmd = arr[0].toLowerCase();

        switch (cmd) {
        case "help":
            return new HelpCommand();
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
                throw new DukeException ("Please enter a keyword to find");
            }
        case "todo":
        case "event":
        case "deadline":
            try {
                return new AddCommand(cmd, arr[1]);
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("The description of the command '" + cmd + "' cannot be empty!");
            }
        default:
            throw new DukeException("I'm sorry I do not understand what you mean. " +
                    "Please enter 'help' to see all supported commands.");
        }


        /*
        The Parser parses the full command as such:
        1. Identify the command type - is it an add command, a delete command, an exit command, a finish task command
        2. Create a new Command object: e.g. return new AddCommand(tasktype, taskname);
         */
    }
}
