package duke.parser;

import duke.command.*;
import duke.exception.IncompleteCommandError;
import duke.exception.UnknownCommandException;
import duke.task.TaskList;

import java.util.Optional;

public class ParserManager {
    private TaskList taskList;

    /**
     * Parse given user input and returns Optional containing null or valid command
     * @param taskList - list containing all existing tasks
     * @param fullCommand - command given by user input
     * @return Optional containing either valid command or null (when exception is thrown)
     */
    public Optional<Command> parseCommand(TaskList taskList, String fullCommand) {
        this.taskList = taskList;
        String[] commandDescription = fullCommand.trim().split("\\s+", 2);
        commandDescription[0] = commandDescription[0].toLowerCase();
        String taskName = commandDescription[0];
        try {
            // Special case since only word "list" is required
            if(taskName.equals("list")) { return ListCommandParser.parse(fullCommand); }
            // Checks if command is empty
            this.checkCommandEmpty(commandDescription);

            switch (taskName) {
            case "delete":
                return DeleteCommandParser.parse(commandDescription, this.taskList.size());
            case "done":
                return DoneCommandParser.parse(commandDescription, this.taskList.size());
            case "todo":
                return AddCommandParser.parseWithoutDate(commandDescription);
            case "deadline":
            case "event":
                return AddCommandParser.parseWithDate(commandDescription);
            case "find":
                return FindCommandParser.parse(commandDescription);
            default:
                throw new UnknownCommandException(commandDescription[0]);
            }
        } catch (UnknownCommandException e) {
            System.out.println(e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            this.printExceedListMessage(this.taskList.size());
        } catch (NumberFormatException e) {
            this.printInvalidStatementMessage(fullCommand);
        }
        return Optional.empty();
    }

    /**
     * Throws error if the given command is empty
     * @param commandDescription - array of strings containing command description
     * @throws IncompleteCommandError - throws error if the command is not in complete format
     */
    private void checkCommandEmpty(String[] commandDescription) throws IncompleteCommandError {
        if (commandDescription.length == 1) {
            throw new IncompleteCommandError("empty", commandDescription[0]);
        }
    }

    /**
     * Prints out message after exceeding list bounds
     * @param size - Current size of list
     */
    private void printExceedListMessage(int size) {
        System.out.println("There is/are only " + size + " item(s) in the list :( ");
    }

    /**
     * Prints out message after invalid statement
     * @param fullCommand - Invalid command
     */
    private void printInvalidStatementMessage(String fullCommand) {
        System.out.println("☹ OOPS!!! The statement: \"" + fullCommand + "\" is invalid. ");
    }
}
