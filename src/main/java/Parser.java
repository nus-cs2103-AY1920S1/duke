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
        assert !fullCommand.isEmpty() : "No command entered";

        if (fullCommand.equals("bye")) {
            return new ExitCommand();
        } else if (fullCommand.equals("list")) {
            return new ListCommand();
        } else {
            String[] fields = fullCommand.split(" ", 2);
            String command = fields[0];
            if (command.equals("done")) {
                if (secondFieldIsEmpty(fields)) {
                    throw new DukeException("Please indicate the task number!");
                }
                int doIndex = Integer.parseInt(fullCommand.substring(5));
                return new DoneCommand(doIndex);
            } else if (command.equals("find")) {
                if (secondFieldIsEmpty(fields)) {
                    throw new DukeException("Please indicate what you want to find!");
                }
                return new FindCommand(fullCommand.substring(5));
            } else if (command.equals("edit")) {
                if (secondFieldIsEmpty(fields)) {
                    throw new DukeException("Please indicate what you want to edit!");
                }
                return new EditCommand(fullCommand.substring(5));
            } else if (command.equals("delete")) {
                if (secondFieldIsEmpty(fields)) {
                    throw new DukeException("Please indicate what you want to delete!");
                }
                int deleteIndex = Integer.parseInt(fullCommand.substring(7));
                return new DeleteCommand(deleteIndex);
            } else if (command.equals("todo") || command.equals("deadline") || command.equals("event")) {
                if (secondFieldIsEmpty(fields)) {
                    throw new DukeException("☹ OOPS!!! The description of a " + command + " cannot be empty.");
                }
                return new AddCommand(fullCommand);
            } else {
                throw new DukeException("Please enter a valid command");
            }
        }
    }

    //Used to check if the description field is empty
    private static boolean secondFieldIsEmpty(String[] fields) {
        return fields.length <= 1;
    }
}
