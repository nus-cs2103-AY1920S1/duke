package duke.command;

import duke.data.Storage;
import duke.exception.DukeException;
import duke.object.TaskList;
import duke.user.Ui;

import java.io.IOException;

public class SetRecurCommand extends Command {
    private int idx;
    private int frequency;

    public SetRecurCommand(int idx, int frequency) {
        this.frequency = frequency;
        this.idx = idx;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        try {
            System.out.println(tasks.getTask(this.idx));
            System.out.println(this.frequency);
            ui.confirmRecur(tasks.getTask(this.idx).setRecur(frequency), frequency);
        } catch(DukeException e) {
            System.out.println("This task cannot recur");
        }
        storage.saveTasks(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
