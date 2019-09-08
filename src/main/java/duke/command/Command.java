package duke.command;

import duke.model.Task;
import duke.storage.Storage;
import duke.ui.Ui;

import java.util.List;

public interface Command {
    /**
     * Executes the command.
     *
     * @param tasks   List of Tasks to operate on.
     * @param ui      User interface to display messages to the user.
     * @param storage Some sort of data persistence layer to load or save data to.
     */
    void execute(List<Task> tasks, Ui ui, Storage storage);

    /**
     * Returns whether Duke should terminate after executing this command.
     *
     * @return Whether Duke should terminate after executing this command.
     */
    default boolean isExit() {
        return false;
    }
}

