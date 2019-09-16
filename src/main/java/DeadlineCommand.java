import java.io.IOException;

public class DeadlineCommand extends Command {

    public DeadlineCommand(String action, String variable) {
        super(action, variable);
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        String deadlineOutput = ui.printGI() + "\n";
//        System.out.println(ui.printGI());
        Task assignmentToDo = new Deadline(action, variable);
        tasks.addToTaskList(assignmentToDo);
        storage.addToFile(Storage.file, assignmentToDo.toString());
//        Ui.printNumOfTasks();
        deadlineOutput += "  " + assignmentToDo.toString() + "\n";
        deadlineOutput += Ui.printNumOfTasks();
        return deadlineOutput;
    }
}