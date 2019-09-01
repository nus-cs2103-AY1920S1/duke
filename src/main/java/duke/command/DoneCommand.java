package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.text.ParseException;

/**
 * This class controls the done command.
 */
public class DoneCommand extends Command {

    private int indexToDone;

    public DoneCommand(int indexToDone) {
        this.indexToDone = indexToDone - 1;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.getTasks().get(indexToDone);
        task.markAsDone();
        return ui.done(task);
    }
}
