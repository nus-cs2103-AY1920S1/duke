package duke.parser;

import duke.Duke;
import duke.command.*;
import duke.command.UnknownCommandException;
import duke.task.TaskList;

public class ParserManager {
    private TaskList taskList;

    /**
     * Parse given user input and returns Optional containing null or valid command
     * @param taskList - list containing all existing tasks
     * @param fullCommand - command given by user input
     * @return Optional containing either valid command or null (when exception is thrown)
     */
    public Command parseCommand(TaskList taskList, String fullCommand, CommandHistoryStack commandHistory) throws UnknownCommandException, RuntimeException {
        this.taskList = taskList;
        String[] commandDescription = fullCommand.trim().split("\\s+", 2);
        Commands commandType = checkValidCommand(commandDescription);

        // Check for Undo case
        if(commandType.equals(Commands.UNDO)) {
            return UndoCommandParser.parse(fullCommand, commandHistory);
        }

        Command command;
        switch (commandType) {
        case LIST:
            command = ListCommandParser.parse(fullCommand);
            break;
        case DELETE:
            command = DeleteCommandParser.parse(commandDescription, this.taskList.size());
            break;
        case DONE:
            command = DoneCommandParser.parse(commandDescription, this.taskList.size());
            break;
        case TODO:
            command = AddCommandParser.parseWithoutDate(commandDescription);
            break;
        case DEADLINE:
        case EVENT:
            command = AddCommandParser.parseWithDate(commandDescription);
            break;
        case FIND:
            command = FindCommandParser.parse(commandDescription);
            break;
        default:
            throw new UnknownCommandException(commandDescription[0]);
        }
        commandHistory.update(command, taskList);
        return command;
    }

    /**
     * Checks if the given String contains a valid command
     * @param commandDescription - array of strings containing command description
     * @return Commands containing valid command type
     * @throws UnknownCommandException - thrown when given string does not match valid commands
     */
    private Commands checkValidCommand(String commandDescription[]) throws UnknownCommandException {
        try {
            return Commands.valueOf(commandDescription[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new UnknownCommandException(commandDescription[0]);
        }
    }
}
