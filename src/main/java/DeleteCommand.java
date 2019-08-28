import java.io.IOException;

public class DeleteCommand extends Command {
    private int deleteIndex;

    public DeleteCommand(int deleteIndex){
        this.deleteIndex = deleteIndex;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        Task chosen_Task = tasks.getTask(deleteIndex - 1);
        tasks.removeTask(deleteIndex - 1);
        try {
            storage.deleteFromFile(deleteIndex);
            System.out.println("Noted. I've removed this task:\n  " + chosen_Task.toString());
            System.out.println("Now you have " + tasks.tasks.size() +
                    (tasks.tasks.size() == 1 ? " task" : " tasks") + " in the list.");
        } catch (IOException ex) {
            System.out.print("Error when deleting task from saved file");
        }
    }
}
