/**
 * Encapsulates a user command to delete a task from the task list.
 */
public class DeleteCommand extends Command {

    int indexToRemove = -1;

    public void setIndexToRemove(int indexToRemove) {
        this.indexToRemove = indexToRemove;
    }

    /**
     * Overridden method. Executes the delete command.
     * @param tasks list of tasks
     * @param ui user interface
     * @param storage storage file
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task toBeRemoved = tasks.getTask(indexToRemove);
        tasks.removeTask(indexToRemove);
        System.out.println("Noted. I've removed this task:");
        System.out.println(toBeRemoved);
        System.out.println("Now you have " + tasks.getSize() + " tasks in the list.");
        storage.updateChanges(tasks.getDukeTaskList());
    }

    @Override
    public String executeForGui(TaskList tasks, Ui ui, Storage storage) {
        Task toBeRemoved = tasks.getTask(indexToRemove);
        tasks.removeTask(indexToRemove);
        StringBuilder sb = new StringBuilder();
        sb.append("Noted. I've removed this task:");
        sb.append("\n");
        sb.append(toBeRemoved);
        sb.append("\n");
        sb.append("Now you have " + tasks.getSize() + " tasks in the list.");
        storage.updateChanges(tasks.getDukeTaskList());
        return sb.toString();
    }
}
