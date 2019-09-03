package duke.command;

import duke.component.DukeException;
import duke.component.Storage;
import duke.component.TaskList;
import duke.component.Ui;

import java.io.IOException;

/**
 * Abstract Command Class.
 */
public abstract class Command {
    /**
     * Abstract method to execute respective command.
     * @param taskList list of tasks.
     * @param storage storage to store inside hard disk.
     * @param ui ui for user interaction.
     * @return boolean indication of successful or unsuccessful running of command.
     * @throws DukeException when Duke error occurs.
     * @throws IOException when IO error occurs.
     */
    public abstract String executeCommand(TaskList taskList, Storage storage, Ui ui)
            throws DukeException, IOException;
}
