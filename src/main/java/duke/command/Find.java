package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a find command.
 */
public class Find extends Command {

    private String target;

    public Find(String target) {
        this.target = target;
    }

    @Override
    public String exec(Storage storage, TaskList tasks, Ui ui) {
        TaskList targets = tasks.find(this.target);
        try {
            return ui.printFind(targets);
        } catch (DukeException ex) {
            return ui.showDukeException(ex);
        }
    }
}
