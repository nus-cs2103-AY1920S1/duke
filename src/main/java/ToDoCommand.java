import java.io.IOException;

public class ToDoCommand extends Command {

    public ToDoCommand(String action) {
        super(action);
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        assert tasks != null;
        assert ui != null;
        assert storage != null;
        String todoOutput = ui.printGI() + "\n";
        Task assignmentToDo = new Todo(action);
        tasks.addToTaskList(assignmentToDo);
        storage.addToFile(Storage.file, assignmentToDo.toString());
        todoOutput += "  " + assignmentToDo.toString() + "\n";
        todoOutput += Ui.printNumOfTasks();
        return todoOutput;
    }
}
