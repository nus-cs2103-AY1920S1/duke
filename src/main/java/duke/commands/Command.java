/**
 * This abstract class of Command contains the methods execute(...) and isExit() which are implemented in its child
 * classes, where each is a more specific command parsed from the inputs to Duke.
 */
package duke.commands;
import duke.exceptions.DukeException;
import duke.managers.TaskList;
import duke.managers.Ui;
import duke.managers.Storage;
import java.io.IOException;

public abstract class Command {

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException;

    public abstract boolean isExit();
}
