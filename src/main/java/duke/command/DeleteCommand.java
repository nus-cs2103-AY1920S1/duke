package duke.command;

import duke.DukeException;
import duke.ListStoreModel;
import duke.Message;
import duke.Storage;
import duke.Ui;
import duke.task.Task;

import java.util.ArrayList;
import java.util.List;

/** Represents a delete Command. A <code>DeleteCommand</code> deletes a command
 * from the task list.
 */
public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Deletes a command from the task list.
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
        tasks.remove(this.index - 1);

        List<String> list = new ArrayList<>();
        list.add("Noted. I've removed this task:");
        list.add(String.format("    %s", task));
        String noun = tasks.size() > 1 ? "tasks" : "task";
        list.add(String.format("Now you have %d %s in the list.", tasks.size(), noun));
        ui.print(new Message(list));
    }

    /**
     * Overriding equals to compare two DeleteCommand objects.
     * @param o object to be compared with current DeleteCommand instance.
     * @return True if the index is equivalent.
     */
    @Override
    public boolean equals(Object o) {
        return o instanceof DeleteCommand && this.index == ((DeleteCommand) o).index;
    }
}
