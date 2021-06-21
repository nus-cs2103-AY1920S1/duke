package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents an addition command.
 */
public class Add extends Command {

    private String type;
    private String description;

    /**
     * Initiates an Add object.
     * @param type type of new task
     * @param description description of the new task
     */
    public Add(String type, String description) {
        this.type = type;
        this.description = description;
    }

    @Override
    public String exec(Storage storage, TaskList tasks, Ui ui) {
        try {
            tasks.add(type, description);
            storage.write(tasks);
            return ui.add(tasks);
        } catch (DukeException ex) {
            return ui.showDukeException(ex);
        }
    }
}
