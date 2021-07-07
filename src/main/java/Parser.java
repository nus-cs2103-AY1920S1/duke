import java.util.InputMismatchException;

public class Parser {

    /**
     * Parses user input into Command object.
     *
     * @param command the input command from user.
     * @return the Command object parsed from user input command.
     * @throws DukeIllegalArgumentException if user input command does not meet specified format.
     */
    public static Command parse(String command) throws DukeIllegalArgumentException {
        if (command.isBlank()) {
            throw new DukeIllegalArgumentException("User input cannot be empty.");
        }
        try {
            assert command.length() != 0 : "user input command is blank";
            String[] cmdList = command.split(" ");
            String keyword = cmdList[0];
            switch (keyword.toLowerCase()) {
            case "bye":
                return new ExitCommand();

            case "find":
                String taskToFind = cmdList[1];
                return new FindCommand(taskToFind);

            case "list":
                return new ListCommand();

            case "done":
                int idxToMarkAsDone = Integer.parseInt(cmdList[1]) - 1;
                return new DoneCommand(idxToMarkAsDone);

            case "delete":
                int idxToBeRemoved = Integer.parseInt(cmdList[1]) - 1;
                return new DeleteCommand(idxToBeRemoved);

            case "update":
                int idxToUpdate = Integer.parseInt(cmdList[1]) - 1;
                return new UpdateCommand(idxToUpdate, command.substring(9).trim());

            default:  //it will be an AddCommand or an invalid command
                Task taskToBeAdded = handleNewTask(keyword, command);
                return new AddCommand(taskToBeAdded);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeIllegalArgumentException("Invalid input. Please ensure input format is correct.");
        } catch (NumberFormatException e) {
            throw new DukeIllegalArgumentException("User input is not a number.");

        }
    }

    /**
     * Creates appropriate Task based on given keyword and user input command.
     *
     * @param keyword is the type of Task specified.
     * @param cmd is the original user input command.
     * @return the Task object based on keyword.
     * @throws DukeIllegalArgumentException if user input does not match any Task keywords.
     */
    public static Task handleNewTask(String keyword, String cmd) throws DukeIllegalArgumentException {

        switch (keyword.toLowerCase()) {
        case "deadline":
            return Deadline.genDeadlineTask(cmd);

        case "event":
            return Event.genEventTask(cmd);

        case "todo":
            return Todo.genTodoTask(cmd);

        default:
            throw new DukeIllegalArgumentException("I'm sorry, but I don't know what that means :-(");
        }
    }
}
