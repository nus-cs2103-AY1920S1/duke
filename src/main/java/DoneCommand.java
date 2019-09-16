import exception.DukeException;
import tasks.Task;

import java.io.IOException;
import java.text.ParseException;

public class DoneCommand extends Command {
    public DoneCommand(String desc) {
        super(desc);
    }

    public String execute(Storage storage, TaskList taskList, Ui ui) throws IOException, ParseException {
        try {
            if (desc.length() <= 4) {
                throw new DukeException();
            }
            String[] descArr = desc.split(" ");
            int num = getTaskNum(descArr);
            if (num >= taskList.getSize()) {
                throw new DukeException();
            }
            Task task = taskList.markTaskAsDone(num);
            storage.updateTaskInFile(taskList.getList());
            return ui.showTaskDone(task);
        } catch (DukeException e) {
            return ui.showNoSuchTaskError();
        }
    }
}
