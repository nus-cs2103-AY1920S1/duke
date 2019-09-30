package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a command to print all existing tasks.
 */
public class PrintList extends Command {

    @Override
    public String exec(Storage storage, TaskList tasks, Ui ui) {
        try {
            return ui.printList(tasks);
        } catch (DukeException ex) {
            return ui.showDukeException(ex);
        }
    }
}
