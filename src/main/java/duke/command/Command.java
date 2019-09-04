package duke.command;

import duke.DukeException;
import duke.Parser;
import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * This class provides the skeleton for a Duke Command.
 * To implement a Command, the programmer only needs to extend this class and provide an implementation for
 * the {@link #getName()} and {@link #execute(TaskList, Ui, Storage)} methods.
 */
public abstract class Command {
    protected Parser parser = new Parser();
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
     * @param tasks    TaskList to read and manipulate
     * @param ui       the user interface to input and output from and to
     * @param storage  the persistent storage to save and load to and from
     * @throws DukeException
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
}
