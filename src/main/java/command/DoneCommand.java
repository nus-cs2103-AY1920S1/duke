package command;

import main.Storage;
import main.TaskList;
import main.UI;
import task.Task;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class DoneCommand extends Command {
    int taskNum;

    public DoneCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    public void execute(TaskList tl, Storage st) {
        ArrayList<Task> list = tl.getTaskList();
        Task currTask = list.get(taskNum);
        currTask.setDone();
        UI.done(currTask);

        st.writeToFile(list);
    }
}
