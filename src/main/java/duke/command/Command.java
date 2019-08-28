package duke.command;

import duke.component.DukeException;
import duke.component.Storage;
import duke.component.TaskList;
import duke.component.Ui;

import java.io.IOException;

/**
 * Abstract Command Class
 */
public abstract class Command {
    /**
     * Abstract method to execute respective command
     * @param taskList list of tasks
     * @param storage storage to store inside hard disk
     * @param ui ui for user interaction
     * @return
     * @throws DukeException
     * @throws IOException
     */
    public abstract boolean executeCommand(TaskList taskList, Storage storage, Ui ui) throws DukeException, IOException;
}
