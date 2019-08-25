/**
 * Delete Command. Deletes the Task from the given 1-based index.
 */
public class DeleteCommand extends Command {
    /** Index of the task to be deleted. */
    private int index;

    /**
     * Constructor. Sets isExit to false as it is not an exit command.
     * @param index
     */
    public DeleteCommand(int index) {
        this.index = index;
        isExit = false;
    }

    /**
     * Behaviour of Delete Command. Deletes the task.
     * @param taskList TaskList of tasks.
     * @param ui Ui object for user interaction.
     * @param storage Storage object for data file.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Task task = taskList.deleteTask(index);
        String indent = ui.getIndent();
        String message;
        if (task == null) {
            message = indent + "Index out of range, no task found.";
        } else {
            message = new StringBuilder()
                    .append(indent + "Noted. I've removed this task:\n")
                    .append(indent + "  " + task + "\n")
                    .append(indent + "Now you have " + taskList.getTotalTasks() + " tasks in the list.")
                    .toString();
        }
        System.out.println(message);
        storage.write(taskList);
    }
}
