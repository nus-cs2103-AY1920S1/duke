package seedu.duke.Command;

import seedu.duke.Storage.Storage;
import seedu.duke.TaskList.TaskList;
import seedu.duke.Ui.Ui;

import java.io.IOException;

public class DoneCommand extends Command {

    private int taskNo;
    private TaskList taskList;

    public DoneCommand(int taskNo) {
        this.taskNo = taskNo;
    }

    /**
     * execute the command of marking a task as done
     * @param taskList
     * @param storage
     * @throws IOException
     */
    @Override
    public void execute(TaskList taskList, Storage storage) throws IOException {
        taskList.list.get(taskNo - 1).markAsDone();
        this.taskList = taskList;
        storage.save(taskList);
    }

    @Override
    public String getResponse() {
        StringBuilder str = new StringBuilder();
        str.append("Nice! I've marked this task as done:\n");
        str.append(taskList.list.get(taskNo - 1).toActionString());
        return str.toString();
    }

    @Override
    public boolean isExit() {
        return isExit;
    }
}
