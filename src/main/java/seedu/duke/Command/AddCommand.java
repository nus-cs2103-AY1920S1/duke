package seedu.duke.Command;

import seedu.duke.Storage.Storage;
import seedu.duke.TaskList.TaskList;
import seedu.duke.TaskList.Task;

import java.io.IOException;

public class AddCommand extends Command {

    protected Task task;
    protected TaskList taskList;

    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * execute the command of adding task
     * @param taskList
     * @param storage
     * @throws IOException
     */
    @Override
    public void execute(TaskList taskList, Storage storage) throws IOException {
        taskList.addList(task);
        this.taskList = taskList;
        storage.save(taskList);
    }

    @Override
    public String getResponse() {
        StringBuilder str = new StringBuilder();
        str.append("Got it. I've added this task:\n");
        str.append(task.toActionString() + "\n");
        str.append("Now you have " + taskList.list.size()
                + " tasks in the list");
        return str.toString();
    }
    /**
     *
     * @return boolean
     *
     */
    @Override
    public boolean isExit() {
        return isExit;
    }
}
