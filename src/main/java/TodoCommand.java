import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class TodoCommand extends Command {
    private String todoName;

    public TodoCommand(String todoName) {
        this.todoName = todoName;
    }

    public void execute(TaskList tl, Storage st) throws IOException {
        ArrayList<Task> list = tl.list;

        Task todo = new Todo(todoName);

        tl.addTaskList(todo);
        st.writeToFile(tl.list);

        UI.newTask(tl.list);
    }
}
