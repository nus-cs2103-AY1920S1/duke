import exception.DukeException;
import tasks.Task;

import java.io.IOException;

public class PriorityCommand extends Command {
    public PriorityCommand(String description) {
        super(description);
    }

    public String execute(Storage storage, TaskList taskList, Ui ui) throws
            IOException {
        try {
            if (description.length() <= 8) {
                throw new DukeException();
            }
            assert (description.length() > 8);
            String[] descArr = description.split(" ");
            int taskNum = getTaskNum(descArr);
            if (taskNum >= taskList.getSize()) {
                throw new DukeException();
            }
            assert (taskNum < taskList.getSize());
            String priority = descArr[2].toLowerCase();
            Task task = taskList.setTaskPriority(taskNum, priority);
            storage.updateTaskInFile(taskList.getList());
            return ui.showPrioritySet(task);
        } catch (DukeException e) {
            return ui.showUnknownCommandError();
        }
    }
}
