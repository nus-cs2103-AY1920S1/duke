package command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import exception.DukeException;
import exception.DukeInvalidTaskIndexException;
import java.io.IOException;

/**
 * Represents a Command which marks a task from the TaskList as done.
 */
public class DoneCommand extends Command {
    private int index;

    /**
     * Creates a DoneCommand with a given index to mark a task from the TaskList as done.
     * @param index Task index to be marked as done.
     */
    public DoneCommand(int index) {
        this.index = index - 1;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        if (tasks.getSize() == 0) {
            throw new DukeException("You have no tasks to do.");
        } else if (index < 0 || index >= tasks.getSize()) {
            throw new DukeInvalidTaskIndexException("do", tasks.getSize());
        } else {
            ui.print("Nice! I've marked this task as done:\n  " + tasks.getTask(index));
            tasks.doneTask(index);
            storage.save(tasks);
        }
    }

    public boolean isExit() {
        return false;
    }
}