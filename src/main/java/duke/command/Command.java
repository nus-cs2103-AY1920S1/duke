package duke.command;

import duke.DukeException;
import duke.Model;
import duke.io.UiOutput;
import duke.util.ArgumentParser;
import duke.Storage;

/**
 * This class provides the skeleton for a Duke Command.
 * To implement a Command, the programmer only needs to extend this class and provide an implementation for
 * the {@link #getName()} and {@link #execute(Model, UiOutput, Storage)} methods.
 */
public abstract class Command {
    protected ArgumentParser argumentParser = new ArgumentParser();
    protected String[] args;

    public Command(String[] args) {
        this.args = args;
    }

    /**
     * Returns {@code true} if this Command is a ByeCommand.
     *
     * @return  {@code true} if this Command is a ByeCommand
     */
    public boolean isExit() {
        return (this instanceof ByeCommand);
    }

    /**
     * Returns the name of this Command. The name can be used when parsing to identify
     * the Command type.
     *
     * @return  the name of this Command
     */
    public abstract String getName();

    /**
     * Executes this Command with the given resources.
     *
     * @param model    data model to read and manipulate
     * @param uiOutput       the user interface to input and output from and to
     * @param storage  the persistent storage to save and load to and from
     * @throws DukeException  if an error occurs during execution
     */
    public abstract void execute(Model model, UiOutput uiOutput, Storage storage) throws DukeException;
}
