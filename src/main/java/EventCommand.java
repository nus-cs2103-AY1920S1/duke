import java.io.IOException;

public class EventCommand extends Command {

    public EventCommand(String action, String variable) {
        super(action, variable);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        System.out.println(ui.printGI());
        Task assignmentToDo = new Event(action, variable);
        tasks.addToTaskList(assignmentToDo);
        storage.addToFile(Storage.file, assignmentToDo.toString());
        Ui.printNumOfTasks();
        System.out.println("  " + assignmentToDo.toString());
    }
}