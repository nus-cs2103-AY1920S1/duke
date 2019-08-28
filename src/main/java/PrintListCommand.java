/**
 * Represents a PrintListCommand which prints all Tasks in the TaskList.
 */
public class PrintListCommand extends Command {
    /**
     * Constructor of PrintListCommand.
     */
    public PrintListCommand() {
        super();
    }

    /**
     * Execute print list command. Prints all Tasks in the TaskList.
     * @param tasks Print the Tasks in TaskList.
     * @param ui Performs actions on Ui if required.
     * @param storage Saves to Storage or loads from Storage if required.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println("     Here are the tasks in your list:");
        for (int i = 1; i < tasks.size() + 1; i++) {
            System.out.println(String.format("     %d. %s", i, tasks.get(i)));
        }
    }

    /**
     * Returns true as it is not an ExitCommand.
     * @return Boolean value of whether Duke should continue running.
     */
    @Override
    public boolean isRunning() {
        return true;
    }

    /**
     * Returns true if PrintCommand is of same instance as object. Used for testing.
     * @param object Object to compare equality with.
     * @return Boolean value of whether current PrintCommand equals input Object.
     */
    @Override
    public boolean equals(Object object) {
        return (object instanceof PrintListCommand);
    }
}
