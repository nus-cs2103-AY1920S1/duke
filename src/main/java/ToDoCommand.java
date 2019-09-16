import java.io.IOException;

public class ToDoCommand extends Command {

    public ToDoCommand(String action) {
        super(action);
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        String todoOutput = ui.printGI() + "\n";
        //System.out.println(ui.printGI());
        Task assignmentToDo = new Todo(action);
        tasks.addToTaskList(assignmentToDo);
        storage.addToFile(Storage.file, assignmentToDo.toString());
        //System.out.println(Ui.printNumOfTasks());
        todoOutput += "  " + assignmentToDo.toString() + "\n";
        todoOutput += Ui.printNumOfTasks();
        return todoOutput;
    }
}
