import exception.DukeException;
import tasks.Task;

import java.io.IOException;

public class DeleteCommand extends Command {
    public DeleteCommand(String desc) {
        super(desc);
    }

    public String execute(Storage storage, TaskList taskList, Ui ui)
            throws IOException {
        try {
            if (desc.length() <= 6) {
                throw new DukeException();
            }
            String[] descArr = desc.split(" ");
            int num = getTaskNum(descArr);
            if (num >= taskList.getSize()) {
                throw new DukeException();
            }
            Task removed = taskList.removeTask(num);
            storage.updateTaskInFile(taskList.getList());
            return ui.showTaskRemoved(removed, taskList.getSize());
        } catch (DukeException e) {
            return ui.showNoSuchTaskError();
        }
    }
}
