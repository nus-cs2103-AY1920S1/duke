package seedu.duke.Command;

import seedu.duke.Storage.Storage;
import seedu.duke.TaskList.Task;
import seedu.duke.TaskList.TaskList;
import seedu.duke.Ui.Ui;

import java.io.IOException;

public class DeleteCommand extends Command {

    private int taskNo;
    private TaskList taskList;
    private Task task;

    public DeleteCommand(int taskNo) {
        this.taskNo = taskNo;
    }

    /**
     * execute the command of deleting the task
     * @param taskList
     * @param storage
     * @throws IOException
     */
    @Override
    public void execute(TaskList taskList, Storage storage) throws IOException {
        this.task = taskList.list.get(taskNo - 1);
        taskList.delete(taskNo);
        this.taskList = taskList;
        storage.save(taskList);
    }

    @Override
    public String getResponse() {
        StringBuilder str = new StringBuilder();
        str.append("Noted. I've removed this task: \n");
        str.append(task.toActionString() + "\n");
        str.append("Now you have " + taskList.list.size()
                + " tasks in the list.");
        return str.toString();
    }

    /**
     * check if it is exited
     * @return boolean
     */
    @Override
    public boolean isExit() {
        return isExit;
    }
}
