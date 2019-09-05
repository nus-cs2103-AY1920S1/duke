/**
 * Deals with loading tasks from the file and saving tasks in the file.
 */
public class Parser {

    /**
     * Returns the appropriate command for later execution of the command.
     * @param fullCommand single line of string from user-input
     * @return appropriate command based on the user-input.
     * @throws DukeException if no valid command is entered.
     */
    public static Command parse(String fullCommand) throws DukeException {
        if (fullCommand.equals("bye")) {
            return new ExitCommand();
        } else if (fullCommand.equals("list")) {
            return new ListCommand();
        } else if (fullCommand.length() >= 4 && fullCommand.substring(0, 4).equals("done")) {
            int doIndex = Integer.parseInt(fullCommand.substring(5));
            return new DoneCommand(doIndex);
        } else if (fullCommand.length() >= 5 && fullCommand.substring(0, 4).equals("find")) {
            return new FindCommand(fullCommand.substring(5));
        } else if (fullCommand.length() >= 6 && fullCommand.substring(0, 6).equals("delete")) {
            int deleteIndex = Integer.parseInt(fullCommand.substring(7));
            return new DeleteCommand(deleteIndex);
        } else if (fullCommand.contains("todo") || fullCommand.contains("deadline") || fullCommand.contains("event")) {
            return new AddCommand(fullCommand);
        } else {
            throw new DukeException("Please enter a valid command");
        }
    }
}
