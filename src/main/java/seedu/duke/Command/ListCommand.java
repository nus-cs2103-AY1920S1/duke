package seedu.duke.Command;

import seedu.duke.Storage.Storage;
import seedu.duke.TaskList.TaskList;
import seedu.duke.Ui.Ui;

import java.io.IOException;

public class ListCommand extends Command {

    private TaskList taskList;

    public ListCommand() {
    }

    /**
     * execute the command of listing out all the task
     * @param taskList
     * @param storage
     * @throws IOException
     */
    @Override
    public void execute(TaskList taskList, Storage storage) throws IOException {
        this.taskList = taskList;
    }

    @Override
    public String getResponse() {
        StringBuilder str = new StringBuilder();
        str.append("Here are the tasks in your list:\n");
        for (int i = 1;i <= taskList.list.size();i++) {
            str.append(i + "."
                    + taskList.list.get(i - 1).toActionString() + "\n");
        }
        return str.toString();
    }

    @Override
    public boolean isExit() {
        return isExit;
    }
}
