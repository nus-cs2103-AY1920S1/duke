package duke.parser;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.IncompleteCommandException;
import duke.command.UnknownCommandException;
import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.task.ToDoTask;

public class AddCommandParser {

    /**
     * Parse new task (without date) based on existing format.
     * @param commandDescription - array of strings containing command description
     * @return Optional containing either valid command or null (when exception thrown)
     * @throws IncompleteCommandException - throws error if the command is not in complete format
     */
    public static Command parseWithoutDate(String[] commandDescription) throws IncompleteCommandException {
        checkCommandEmpty(commandDescription);
        return new AddCommand(new ToDoTask(commandDescription[1]));
    }

    /**
     * Parse new task (with date) based on existing format.
     * @param commandDescription - array of strings containing command description
     * @return Optional containing either valid command or null (when exception thrown)
     * @throws IncompleteCommandException - throws error if the command is not in correct format
     */
    public static Command parseWithDate(String[] commandDescription) throws UnknownCommandException  {
        checkCommandEmpty(commandDescription);
        try {
            String[] taskArray = commandDescription[1].split("/", 2);
            String taskName = taskArray[0].trim();
            String taskType = commandDescription[0].toLowerCase();
            String[] statementAndDate = taskArray[1].split("\\s+", 2);
            DateTimeParser.validateDateFormat(statementAndDate[1]);
            switch (taskType) {
            case "deadline":
                DeadlineTask.verifyTaskStatement(statementAndDate[0].toLowerCase());
                return new AddCommand(new DeadlineTask(taskName, statementAndDate[1]));
            case "event":
                EventTask.verifyTaskStatement(statementAndDate[0].toLowerCase());
                return new AddCommand(new EventTask(taskName, statementAndDate[1]));
            default:
            }
        } catch (IndexOutOfBoundsException e) {
            throw new IncompleteCommandException("incomplete", commandDescription[0]);
        }
        return new AddCommand(null);
    }

    /**
     * Throws error if the given command is empty.
     * @param commandDescription - array of strings containing command description
     * @throws IncompleteCommandException - throws error if the command is not in complete format
     */
    private static void checkCommandEmpty(String[] commandDescription) throws IncompleteCommandException {
        if (commandDescription.length == 1) {
            throw new IncompleteCommandException("empty", commandDescription[0]);
        }
    }
}
