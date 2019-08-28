/**
 * Represents an AddCommand in Duke which adds Tasks to the TaskList
 */

public class AddCommand extends Command {
    /**
     * Represents the task to add.
     */
    Task task;

    /**
     * Constructor for add command.
     * @param task Sets task to add as input.
     */
    public AddCommand(Task task) {
        super();
        this.task = task;
    }

    /**
     * Executes add command. Adds task to TaskList and prints out action.
     * @param tasks Adds task to tasks.
     * @param ui Performs actions on Ui if required.
     * @param storage Saves to Storage or loads from Storage if required.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(this.task);
        System.out.println("     Got it. I've added this task: ");
        System.out.println("       " + this.task);
        System.out.println(String.format("     Now you have %d tasks in the list.", tasks.size()));
    }

    /**
     * Returns true as it is not an ExitCommand.
     * @return Boolean value of whether Duke should continue running.
     */
    @Override
    public boolean isRunning() {
        return true;
    }
}
