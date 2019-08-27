package duke.command;

import duke.exception.DukeException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;
import duke.task.Task;

public class DeleteCommand extends Command {

    private String directive;
    private int position;

    public DeleteCommand(String directive, int position) {
        this.directive = directive;
        this.position = position;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        try {
            Task task = taskList.deleteTaskAtNumber(position);
            ui.notifyTaskDeleted(task, taskList.size());
            storage.writeListToFile(taskList);
        }
        catch (Exception ex) {
            throw new DukeException(ex.getMessage());
        }
    }

}
