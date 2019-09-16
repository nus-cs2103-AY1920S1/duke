import java.io.IOException;

public class EventCommand extends Command {

    public EventCommand(String action, String variable) {
        super(action, variable);
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        String eventOutput = ui.printGI() + "\n";
        Task assignmentToDo = new Event(action, variable);
        tasks.addToTaskList(assignmentToDo);
        storage.addToFile(Storage.file, assignmentToDo.toString());
        //Ui.printNumOfTasks();
        eventOutput += "  " + assignmentToDo.toString() + "\n";
        eventOutput += Ui.printNumOfTasks();
        return eventOutput;
    }
}