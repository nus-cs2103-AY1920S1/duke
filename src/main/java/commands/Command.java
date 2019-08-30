package commands;

import logic.*;

/**
 * Abstraction and Encapsulation of User Commands
 */
public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
}
