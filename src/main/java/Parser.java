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
            String[] fields;
            fields = fullCommand.split(" ", 2);
            String command = fields[0];
            if (command.equals("done")) {
                return generateDoneCommand(fields, fullCommand);
            } else if (command.equals("find")) {
                return generateFindCommand(fields, fullCommand);
            } else if (command.equals("edit")) {
                return generateEditCommand(fields, fullCommand);
            } else if (command.equals("delete")) {
                return generateDeleteCommand(fields, fullCommand);
            } else if (command.equals("todo") || command.equals("deadline") || command.equals("event")) {
                return generateAddCommand(fields, fullCommand);
            } else {
                throw new DukeException("Please enter a valid command");
            }
        }
    }

    //Used to check if the description field is empty
    private static boolean secondFieldIsEmpty(String[] fields) {
        return fields.length <= 1 || fields[1].length() <= 0;
    }

    private static DoneCommand generateDoneCommand(String[] fields, String fullCommand) throws DukeException {
        if (secondFieldIsEmpty(fields)) {
            throw new DukeException("Please indicate the task number!");
        }
        int doIndex;
        try {
            doIndex = Integer.parseInt(fullCommand.substring(5));
        } catch (NumberFormatException e) {
            throw new DukeException("Ensure that you enter in this format:\ndone [task number]");
        }
        return new DoneCommand(doIndex);
    }

    private static FindCommand generateFindCommand(String[] fields, String fullCommand) throws DukeException {
        if (secondFieldIsEmpty(fields)) {
            throw new DukeException("Please indicate what you want to find!");
        }
        return new FindCommand(fullCommand.substring(5));
    }

    private static EditCommand generateEditCommand(String[] fields, String fullCommand) throws DukeException {
        if (secondFieldIsEmpty(fields)) {
            throw new DukeException("Please indicate what you want to edit!");
        }
        return new EditCommand(fullCommand.substring(5));
    }

    private static DeleteCommand generateDeleteCommand(String[] fields, String fullCommand) throws DukeException {
        if (secondFieldIsEmpty(fields)) {
            throw new DukeException("Please indicate what you want to delete!");
        }
        try {
            int deleteIndex = Integer.parseInt(fullCommand.substring(7));
            return new DeleteCommand(deleteIndex);
        } catch (NumberFormatException e) {
            throw new DukeException("Ensure that you enter in this format:\ndelete [task number]");
        }
    }

    private static AddCommand generateAddCommand(String[] fields, String fullCommand) throws DukeException {
        String command = fields[0];
        if (secondFieldIsEmpty(fields)) {
            throw new DukeException("☹ OOPS!!! The description of a " + command + " cannot be empty.");
        }
        return new AddCommand(fullCommand);
    }
}
