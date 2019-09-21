package duke.parser;

import duke.command.*;
import duke.command.exceptions.InvalidCommandDukeException;
import duke.exception.DukeException;

/**
 * Deals with parsing user input.
 */
public class Parser {

    /**
     * Parses the user input and returns the corresponding command.
     * @param input User input
     * @return Command corresponding to the user input.
     * @throws DukeException If the command is invalid.
     */
    public Command parseInput(String input) throws DukeException {
        String cleanedInput = cleanInput(input);
        if (isNullaryCommand(cleanedInput)) {
            return makeNullaryCommand(cleanedInput);
        } else if (isUnaryCommand(cleanedInput)) {
            return makeUnaryCommand(cleanedInput);
        } else if (isDoneCommand(cleanedInput)) {
            return new DoneCommand(cleanedInput);
        } else {
            throw new InvalidCommandDukeException("Unrecognized Command!");
        }
    }

    private boolean isDoneCommand(String input) {
        return input.startsWith("done");
    }

    private String cleanInput(String input) {
        return input.strip().toLowerCase();
    }

    private boolean isNullaryCommand(String cleanedInput) {
        return cleanedInput.startsWith("list") || cleanedInput.startsWith("bye")
                || cleanedInput.startsWith("help") || cleanedInput.startsWith("save");
    }

    private Command makeNullaryCommand(String cleanedInput) {
        if (cleanedInput.startsWith("list")) {
            return new ListCommand();
        } else if (cleanedInput.startsWith("bye")) {
            return new ExitCommand();
        } else if (cleanedInput.startsWith("save")) {
            return new SaveCommand();
        } else {
            return new HelpCommand();
        }
    }

    private boolean isUnaryCommand(String cleanedInput) {
        return cleanedInput.startsWith("delete") || isValidAddCommand(cleanedInput) || cleanedInput.startsWith("find");
    }

    private Command makeUnaryCommand(String cleanedInput) {
        if (cleanedInput.startsWith("delete")) {
            return new DeleteCommand(cleanedInput);
        } else if (cleanedInput.startsWith("find")) {
            return new FindCommand(cleanedInput);
        } else {
            return new AddCommand(cleanedInput);
        }
    }

    private boolean isValidAddCommand(String cleanedInput) {
        return cleanedInput.startsWith("todo ") || cleanedInput.startsWith("deadline ")
                || cleanedInput.startsWith("event ");
    }

}
