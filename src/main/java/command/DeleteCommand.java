package command;

import java.io.IOException;
import java.util.ArrayList;

import main.*;
import exception.DeleteException;
import task.Task;

public class DeleteCommand extends Command {
    int taskNum;

    public DeleteCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    public void execute(TaskList tl, Storage st) {
        try {
            ArrayList<Task> list = tl.getTaskList();
            if (taskNum > list.size() || taskNum <= 0) {
                throw new DeleteException();
            }
            Task task = list.remove(taskNum - 1);
            int listSize =  list.size();

            UI.removedTask(task, listSize);

            st.writeToFile(list);
        } catch (IOException IOe) {
            System.err.println(IOe);
        } catch (DeleteException dE) {
            System.err.println(dE);
        }
    }
}
