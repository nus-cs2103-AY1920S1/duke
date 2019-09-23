package duke.command.creation;

import duke.command.Command;
import error.command.CommandCreationException;

import java.util.Optional;

/**
 * An interface to represent a factory class that is in charge of producing corresponding commands for the program
 * to execute based on user inputs.
 */
public interface CommandFactory {
    /**
     * Parses user input to return an executable command instance for the program.
     * @param input the user's input.
     * @return an executable command instance.
     */
    public Optional<Command> getCommandFromUserInput(String input) throws CommandCreationException;
}
