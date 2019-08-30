package cs2103t.duke.command;

import cs2103t.duke.exception.DukeException;
import cs2103t.duke.exception.InvalidIDException;
import cs2103t.duke.exception.NoIDGivenException;
import cs2103t.duke.file.Storage;
import cs2103t.duke.parse.Parser;
import cs2103t.duke.task.Task;
import cs2103t.duke.task.TaskList;
import cs2103t.duke.ui.Ui;

public class DeleteCommand extends Command {
    private String taskId;

    public DeleteCommand(String idString) {
        this.taskId = idString;
        super.isExit = false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (this.taskId.equals("")) {
            throw new NoIDGivenException("delete");
        }
        int id = Parser.parseStrToInt(this.taskId);
        Task task = tasks.deleteTask(id);

        storage.updateFile(tasks);

        ui.dukeRespond("Noted. I've removed this task:",
                "  " + task.toString(),
                String.format("Now you have %d tasks in the list.", tasks.getSize()));
    }
}
