package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class DoneCommand extends Command {
    protected int index;

    /**
     * Initialise a DoneCommand.
     *
     * @param commandContent User input string less command word.
     * @throws DukeException If user input argument is not a single integer.
     */
    public DoneCommand(String commandContent) throws DukeException {
        super(commandContent);

        if (!commandContent.matches("(0|[1-9]\\d*)")) {
            throw new DukeException("OOPS!!! Index must be a positive integer.");
        }

        index = Integer.parseInt(commandContent);

        assert index > 0 : "index cannot be a negative integer";
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.get(index);
        task.setDone();
        storage.save(tasks);
        ui.showTaskDoneMsg(task);
    }
}
