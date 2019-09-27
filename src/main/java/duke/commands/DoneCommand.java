package duke.commands;

import duke.errands.Task;
import duke.exception.DukeException;
import duke.utility.Storage;
import duke.utility.TaskList;
import duke.utility.Ui;

public class DoneCommand extends Command {

    public DoneCommand(String input) {
        super(input);
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage store) throws DukeException {
        int toDone = Integer.parseInt(this.input);
        assert toDone <= tasks.count();
        if (toDone > tasks.count()) {
            throw new DukeException("☹ OOPS!!! No such item in the list!");
        }
        Task doneTask = tasks.fetch(toDone);
        doneTask.markAsDone();
        store.write(tasks);
        return ui.doneTask(doneTask);
    }
}
