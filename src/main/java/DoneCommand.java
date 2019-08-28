import java.io.IOException;

public class DoneCommand extends Command {
    private int doIndex;

    /**
     * @param doIndex the task number done by the user
     */
    public DoneCommand(int doIndex) {
        this.doIndex = doIndex;
    }

    /**
     * @return whether command is an ExitCommand
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Marks the specified task as done.
     * @param tasks TaskList containing the user's saved tasks
     * @param ui Ui object to handle the user input
     * @param storage storage object to determine where the executed results are stored
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task chosen_Task = tasks.getTask(doIndex - 1);
        chosen_Task.markAsDone();
        try {
            storage.updateTaskInFile(doIndex);
            System.out.println("Nice! I've marked this task as done:\n  " +
                    chosen_Task.toString());
        } catch (IOException ex) {
            System.out.println("Can't update task in the file");
        }
    }
}
