public class AddCommand extends Command {
    private Task task;

    /**
     * Creates an AddCommand object
     * Adds the task to the tasklist
     * @param task
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add
    }
}
