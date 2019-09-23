package duke.command;

import error.command.CommandNotExecutedException;
import error.ui.UiException;

import java.util.Optional;

/**
 * Interface for commands to be executed by the program.
 */
public interface Command {
    /**
     * Carries out command execution logic.
     * @throws UiException if ui fails unexpectedly
     */
    public abstract void execute() throws UiException;

    /**
     * Returns a functional interface that undoes the actions carried out by this command.
     * @return optional of the functional interface
     */
    public abstract Optional<UndoAction> getUndoAction() throws CommandNotExecutedException;

}
