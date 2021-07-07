package duke.command;

import duke.DukeException;
import duke.ListStoreModel;
import duke.Message;
import duke.Storage;
import duke.Ui;
import duke.task.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a done Command. A <code>DoneCommand</code> marks a task in the task
 * list as complete.
 */
public class DoneCommand extends Command {
    private int index;

    public DoneCommand(int index) {
        this.index = index;
    }

    /**
     * Marks a task in the task list as complete.
     * @param lists taskList and peopleList given.
     * @param ui ui object given.
     * @param storage storage given.
     * @throws DukeException if the given index is out of range.
     */
    @Override
    public void execute(ListStoreModel lists, Ui ui, Storage storage) throws DukeException {
        List<Task> tasks = lists.taskList;
        if (this.index > tasks.size()) {
            throw new DukeException("There is no such task!");
        }
        assert this.index >= 0 && this.index <= tasks.size() : "Index is out of range.";
        Task task = tasks.get(this.index - 1);
        task.markAsDone();

        List<String> list = new ArrayList<>();
        list.add("Nice! I've marked this task as done:");
        list.add(String.format("    %s", task));
        ui.print(new Message(list));
    }

    /**
     * Overriding equals to compare two DoneCommand objects.
     * @param o object to be compared with current DoneCommand instance.
     * @return True if the index is equivalent.
     */
    @Override
    public boolean equals(Object o) {
        return o instanceof DoneCommand && this.index == ((DoneCommand) o).index;
    }
}
