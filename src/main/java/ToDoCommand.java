import java.io.IOException;

public class ToDoCommand extends Command {

    public ToDoCommand(String action) {
        super(action);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        System.out.println(ui.printGI());
        Task assignmentToDo = new Todo(action);
        tasks.addToTaskList(assignmentToDo);
        storage.addToFile(Storage.file, assignmentToDo.toString());
        Ui.printNumOfTasks();
        System.out.println("  " + assignmentToDo.toString());
    }
}
