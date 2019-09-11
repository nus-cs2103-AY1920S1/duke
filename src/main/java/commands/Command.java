package commands;

import logic.DukeList;
import logic.Storage;
//import logic.TaskList;
import logic.Ui;
import logic.DukeException;

/**
 * Abstraction and Encapsulation of User Commands.
 */
public abstract class Command {
    public abstract void execute(DukeList tasks, Ui ui, Storage storage) throws DukeException;
}
