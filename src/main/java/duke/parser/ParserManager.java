package duke.parser;

import duke.command.*;
import duke.command.UnknownCommandException;
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
        try {
            String[] commandDescription = fullCommand.trim().split("\\s+", 2);
            Commands commandType = checkValidCommand(commandDescription);
            switch (commandType) {
            case LIST:
                return ListCommandParser.parse(fullCommand);
            case DELETE:
                return DeleteCommandParser.parse(commandDescription, this.taskList.size());
            case DONE:
                return DoneCommandParser.parse(commandDescription, this.taskList.size());
            case TODO:
                return AddCommandParser.parseWithoutDate(commandDescription);
            case DEADLINE:
            case EVENT:
                return AddCommandParser.parseWithDate(commandDescription);
            case FIND:
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

    /**
     * Checks if the given String contains a valid command
     * @param commandDescription - array of strings containing command description
     * @return Commands containing valid command type
     * @throws UnknownCommandException - thrown when given string does not match valid commands
     */
    private Commands checkValidCommand(String commandDescription[]) throws UnknownCommandException{
        try {
            return Commands.valueOf(commandDescription[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new UnknownCommandException(commandDescription[0]);
        }
    }
}
