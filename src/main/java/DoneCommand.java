import java.io.IOException;

public class DoneCommand extends Command {
    private int doIndex;

    /**
     * Instantiates DoneCommand object.
     * @param doIndex the task number done by the user
     */
    public DoneCommand(int doIndex) {
        this.doIndex = doIndex;
    }

    /**
     * Returns whether command is an ExitCommand.
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
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task chosenTask = tasks.getTask(doIndex - 1);
        chosenTask.markAsDone();
        try {
            storage.updateTaskInFile(doIndex);
            return ("Nice! I've marked this task as done:\n  "
                    + chosenTask.toString() + "\n");
        } catch (IOException ex) {
            return "Can't update task in the file\n";
        }
    }
}
