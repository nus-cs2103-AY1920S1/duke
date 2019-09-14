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

            if (keyword.equalsIgnoreCase("bye")) {
                return new ExitCommand();
            } else if (keyword.equalsIgnoreCase("find")) {
                String taskToFind = cmdList[1];
                return new FindCommand(taskToFind);
            } else if (keyword.equalsIgnoreCase("list")) {
                return new ListCommand();

            } else if (keyword.equalsIgnoreCase("done")) {
                int idxToMarkAsDone = Integer.parseInt(cmdList[1]) - 1;
                return new DoneCommand(idxToMarkAsDone);

            } else if (keyword.equalsIgnoreCase("delete")) {
                int idxToBeRemoved = Integer.parseInt(cmdList[1]) - 1;
                return new DeleteCommand(idxToBeRemoved);

            } else { //it will be an AddCommand or an invalid command
                Task taskToBeAdded = handleNewTask(keyword, command);
                return new AddCommand(taskToBeAdded);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeIllegalArgumentException("Invalid input. Please minimally input a <keyword>, <description>, "
                    + "and a <date> and <time> if required");
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
    private static Task handleNewTask(String keyword, String cmd) throws DukeIllegalArgumentException {
        if (keyword.equalsIgnoreCase("deadline")) {
            return Deadline.genDeadlineTask(cmd);

        } else if (keyword.equalsIgnoreCase("event")) {
            return Event.genEventTask(cmd);

        } else if (keyword.equalsIgnoreCase("todo")) {
            return Todo.genTodoTask(cmd);

        } else {
            throw new DukeIllegalArgumentException("I'm sorry, but I don't know what that means :-(");
        }
    }
}
