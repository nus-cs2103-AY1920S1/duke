/**
 * Do Command. Does the task in tasklist by the given 1-based index.
 */
public class DoCommand extends Command {
    /** Index of task to do. */
    private int index;

    /**
     * Constructor. Sets isExit to false as it is not an exit command.
     * @param index Index of the task to do.
     */
    public DoCommand(int index) {
        this.index = index;
        isExit = false;
    }

    /**
     * Behavior of Do Command. Does the task.
     * @param taskList TaskList of tasks.
     * @param ui Ui object for user interaction.
     * @param storage Storage object for data file.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.doTask(index);
        String indent = ui.getIndent();
        String message = indent + "Nice! I've marked this task as done:\n" + indent + "  " + taskList.getTask(index);
        System.out.println(message);
        storage.write(taskList);
    }
}
