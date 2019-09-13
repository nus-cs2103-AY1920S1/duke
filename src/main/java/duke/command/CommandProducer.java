package duke.command;

import error.command.CommandCreationException;

/**
 * Returns a command instance.
 */
@FunctionalInterface
public interface CommandProducer {
    public Command getCommand(String arguments) throws CommandCreationException;
}
