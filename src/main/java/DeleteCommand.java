import java.io.IOException;

public class DeleteCommand extends Command {
    private int deleteIndex;

    /**
     * @param deleteIndex the task number inserted by the user to be deleted
     */
    public DeleteCommand(int deleteIndex){
        this.deleteIndex = deleteIndex;
    }

    /**
     * returns whether the command is an ExitCommand
     * @return whether the command is an ExitCommand
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Deletes given task to the saved task-list and prints
     * a confirmation if the addition of the new task is successful
     * @param tasks TaskList containing the user's saved tasks
     * @param ui Ui object to handle the user input
     * @param storage storage object to determine where the executed results are stored
     * @throws IOException if stream to file cannot be written to or closed.
     */
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
