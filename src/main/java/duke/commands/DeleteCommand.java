package duke.commands;

import duke.errands.Task;
import duke.exception.DukeException;
import duke.utility.Storage;
import duke.utility.TaskList;
import duke.utility.Ui;

public class DeleteCommand extends Command {

    public DeleteCommand(String input) {
        super(input);
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage store) throws DukeException {
        int toRemove = Integer.parseInt(this.input);
        if (toRemove > tasks.count()) {
            throw new DukeException("☹ OOPS!!! No such item in the list!");
        }
        Task deleted =  tasks.fetch(toRemove);
        tasks.delete(toRemove);
        store.write(tasks);
        return ui.deleteTask(deleted, tasks);
    }

}
