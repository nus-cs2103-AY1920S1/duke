import java.io.IOException;

public class DeleteCommand extends Command {

    public DeleteCommand(String action, String variable) {
        super(action, variable);
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        if (variable.equals("all")) {
            storage.writeToFile(Storage.file, "");
            TaskList.listOfTasks.clear();
            Ui.printIndent();
            return "Everything in your list has been removed! "
                    + "Add more tasks to get started again!!!";
        } else {
            int taskNumber = Integer.parseInt(variable);
            String deleteOutput = ui.printRemove() + "\n" + Ui.printDelete(taskNumber) + "\n";
//            System.out.println(ui.printRemove());
//            System.out.println(Ui.printDelete(taskNumber));
            TaskList.listOfTasks.remove(taskNumber - 1);
            storage.writeToFile(Storage.file, "");
            for (Task task : TaskList.listOfTasks) {
                storage.addToFile(Storage.file, task.toString());
            }
            deleteOutput += Ui.printNumOfTasks();
            return deleteOutput;
        }
    }
}
