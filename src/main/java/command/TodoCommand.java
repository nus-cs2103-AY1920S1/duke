package command;

import main.Storage;
import main.TaskList;
import main.UI;
import task.Task;
import task.Todo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class TodoCommand extends Command {
    private String todoName;

    public TodoCommand(String todoName) {
        this.todoName = todoName;
    }

    public void execute(TaskList tl, Storage st) throws IOException {
        ArrayList<Task> list = tl.getTaskList();

        Task todo = new Todo(todoName);

        tl.addTaskList(todo);
        st.writeToFile(list);

        UI.newTask(list);
    }
}
