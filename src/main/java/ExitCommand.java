package duke.command;

import duke.error.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Storage;
import duke.Ui;

public class ExitCommand extends Command {
    protected String command;

    /**
     * Executes operation to generate bye message.
     *
     * @param tasks TaskList to perform changes from
     * @param ui Ui to generate message outputs
     * @param storage Object to save tasks
     * @return String generate message as output from successful operation
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        return ui.showBye();
    }

    public boolean isExit() {
        return true;
    }
}