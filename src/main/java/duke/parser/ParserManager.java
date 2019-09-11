package duke.parser;


import duke.command.Command;
import duke.command.Commands;
import duke.command.UnknownCommandException;
import duke.task.TaskList;

public class ParserManager {

    /**
     * Parse given user input and returns Optional containing null or valid command.
     * @param taskList - list containing all existing tasks
     * @param fullCommand - command given by user input
     * @return Optional containing either valid command or null (when exception is thrown)
     */
    public Command parseCommand(TaskList taskList, String fullCommand)
            throws UnknownCommandException, RuntimeException {
        String[] commandDescription = fullCommand.trim().split("\\s+", 2);
        Commands commandType = checkValidCommand(commandDescription);

        switch (commandType) {
        case LIST:
            return ListCommandParser.parse(fullCommand);
        case DELETE:
            return DeleteCommandParser.parse(commandDescription, taskList.size());
        case DONE:
            return DoneCommandParser.parse(commandDescription, taskList.size());
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
    }

    /**
     * Checks if the given String contains a valid command.
     * @param commandDescription - array of strings containing command description
     * @return Commands containing valid command type
     * @throws UnknownCommandException - thrown when given string does not match valid commands
     */
    private Commands checkValidCommand(String[] commandDescription) throws UnknownCommandException {
        try {
            return Commands.valueOf(commandDescription[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new UnknownCommandException(commandDescription[0]);
        }
    }
}
