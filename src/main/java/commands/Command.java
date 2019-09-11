package commands;

import logic.DukeList;
import logic.Storage;
import logic.Ui;
import logic.DukeException;

/**
 * Abstraction and Encapsulation of User Commands.
 */
public interface Command {
    void execute(DukeList list, Ui ui, Storage storage) throws DukeException;
}
