package cs2103t.duke.command;

import cs2103t.duke.exception.DukeException;
import cs2103t.duke.exception.InvalidIDException;
import cs2103t.duke.exception.NoIDGivenException;
import cs2103t.duke.file.Storage;
import cs2103t.duke.parse.Parser;
import cs2103t.duke.task.Task;
import cs2103t.duke.task.TaskList;
import cs2103t.duke.ui.Ui;

public class DoneCommand extends Command {
    private String taskId;

    public DoneCommand(String idString) {
        this.taskId = idString;
        super.isExit = false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (this.taskId.equals("")) {
            throw new NoIDGivenException("done");
        }
        int id = Parser.parseStrToInt(this.taskId);

        if (id > tasks.getSize() || id < 1) {
            throw new InvalidIDException(this.taskId);
        }

        Task task = tasks.markDone(id);

        storage.updateFile(tasks);

        ui.dukeRespond("Nice! I've marked this task as done:",
                "  " + task.toString());
    }
}
