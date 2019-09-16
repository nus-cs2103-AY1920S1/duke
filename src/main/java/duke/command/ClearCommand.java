package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class ClearCommand extends Command {

    /**
     * Initialise a ClearCommand.
     *
     * @param commandContent User input string less command word.
     * @throws DukeException If additional input other than command word detected.
     */
    public ClearCommand(String commandContent) throws DukeException {
        super(commandContent);

        if (!commandContent.isEmpty()) {
            throw new DukeException("No parameters expected");
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.clear();
        storage.save(tasks);
        ui.showClearSuccessMsg();
    }


}
