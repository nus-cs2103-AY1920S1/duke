import java.util.InputMismatchException;

public class Parser {

    /**
     * parse method will parse command string and return the correct Command.
     * @param command is the user input
     * @return will return the correct command
     */
    public static Command parse(String command, int maxTaskSize) throws DukeIllegalArgumentException {
        if (command.isBlank()) {
            throw new DukeIllegalArgumentException("User input cannot be empty.");
        }
        try {
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
                if (idxToMarkAsDone >= maxTaskSize) {
                    throw new DukeIllegalArgumentException("Invalid input. Please input a valid number between 1 and "
                            + maxTaskSize);
                }
                return new DoneCommand(idxToMarkAsDone);

            } else if (keyword.equalsIgnoreCase("delete")) {
                int idxToBeRemoved = Integer.parseInt(cmdList[1]) - 1;
                if (idxToBeRemoved >= maxTaskSize) {
                    throw new DukeIllegalArgumentException("Invalid input. Please input a valid number between 1 and "
                            + maxTaskSize);
                }
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
     * handleNewTask() will create appropriate Task based on keyword.
     * @param keyword is the different Tasks
     * @param cmd is the full command String
     * @return the appropriate Task
     * @throws InputMismatchException when user does not type correct input
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
