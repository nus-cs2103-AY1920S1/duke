package duke.io;

import duke.exception.IllegalInstructionException;

import duke.command.Command;
import duke.command.AddCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.HelpCommand;

/**
 * The Parser class handles the parsing of user input directly in the duke.ui.Duke program.
 */
public class Parser {
    public static final String COMMAND_LIST = "list";
    public static final String COMMAND_TODO = "todo";
    public static final String COMMAND_DEADLINE = "deadline";
    public static final String COMMAND_EVENT = "event";
    public static final String COMMAND_DONE = "done";
    public static final String COMMAND_DELETE = "delete";
    public static final String COMMAND_FIND = "find";
    public static final String COMMAND_EXIT = "bye";
    public static final String COMMAND_HELP = "help";

    /**
     * Handles parsing of user input within duke.ui.Duke.
     *
     * @param input by the user
     * @return a new Command represented by the input, null otherwise
     * @throws IllegalInstructionException if given input is not in the expected format
     */
    public static Command parse(String input) throws IllegalInstructionException {
        String cmd = input.split("\\s+")[0];
        switch (cmd) {
        case COMMAND_LIST:
            return new ListCommand(input);
        case COMMAND_TODO:
        case COMMAND_DEADLINE:
        case COMMAND_EVENT:
            return new AddCommand(input);
        case COMMAND_FIND:
            return new FindCommand(input);
        case COMMAND_EXIT:
            return new ExitCommand(cmd);
        case COMMAND_DONE:
            return handleDoneCommand(input);
        case COMMAND_DELETE:
            return handleDeleteCommand(input);
        case COMMAND_HELP:
            return new HelpCommand(input);
        default:
            throw new IllegalInstructionException("Sorry! I don't know what that means.");
        }
    }

    /**
     * Returns a Done Command if the command is in the proper form.
     *
     * @param input the input command split by spaces
     * @return a new Done Command
     * @throws IllegalInstructionException if the instruction is not valid
     */
    private static Command handleDoneCommand(String input)
            throws IllegalInstructionException {
        String[] splitCommand = input.split("\\s+");
        validateNumberBasedCommands(splitCommand);
        int index = Integer.parseInt(splitCommand[1]);
        return new DoneCommand(splitCommand[0], index);
    }

    /**
     * Returns a Delete Command if the command is in the proper form.
     *
     * @param input the input command split by spaces
     * @return a new Delete Command
     * @throws IllegalInstructionException if the instruction is not valid
     */
    private static Command handleDeleteCommand(String input)
            throws IllegalInstructionException {
        String[] splitCommand = input.split("\\s+");
        validateNumberBasedCommands(splitCommand);
        int index = Integer.parseInt(splitCommand[1]);
        return new DeleteCommand(splitCommand[0], index);
    }

    /**
     * Validates commands that involve numbers.
     *
     * @param splitCommand command split by spaces
     * @throws IllegalInstructionException if the instruction is not of a valid form
     */
    private static void validateNumberBasedCommands(String[] splitCommand)
            throws IllegalInstructionException {
        boolean isInCorrectFormat = splitCommand.length < 2;
        if (isInCorrectFormat || !isInteger(splitCommand[1])) {
            throw new IllegalInstructionException("Please enter a number after \"delete\"!");
        }
    }

    private static boolean isInteger(String number) {
        try {
            Integer.parseInt(number);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
