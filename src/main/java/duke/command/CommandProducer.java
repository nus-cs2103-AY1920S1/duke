package duke.command;

import error.command.CommandCreationException;

@FunctionalInterface
public interface CommandProducer {
    public Command getCommand(String arguments) throws CommandCreationException;
}
