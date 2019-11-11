package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class ListCommand extends Command {

    /**
     * Initialise a ListCommand.
     *
     * @param commandContent User input string less command word.
     * @throws DukeException If user input argument is not empty.
     */
    public ListCommand(String commandContent) throws DukeException {
        super(commandContent);

        if (!commandContent.isEmpty()) {
            throw new DukeException("No parameters expected");
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.showTasks();
    }
}
