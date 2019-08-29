/**
 * Represents the done command to complete a task.
 */
public class CommandDone extends Command {

    /**
     * The position of the task to be done.
     */
    private int position;

    /**
     * Constructor of the done command.
     * @param i the position of the task to be done.
     */
    public CommandDone (int i) {
        position = i;
    }

    /**
     * Executes the done command on a specific task.
     * @param tasks the task list
     * @param ui the UI
     * @param storage the storage writer
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.done(position);
        String curr = tasks.getList().get(position - 1).toString();
        storage.save(tasks.getList(), tasks.getNoOfTasks());
        ui.printString("Nice! I've marked this task as done:");
        ui.printString(curr);
        storage.save(tasks.getList(), tasks.getNoOfTasks());
    }
}
