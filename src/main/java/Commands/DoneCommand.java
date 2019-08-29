package Commands;

import Functionality.*;

public class DoneCommand extends Command {
    private String args;

    public DoneCommand(String args) {
        this.args = args;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.markTask(args); //will mark Task.Task Obj as done
        storage.updateFile(tasks);
    }
}
