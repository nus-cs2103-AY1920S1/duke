/**
 * Encapsulates a user command to mark a task from the task list as done.
 */
public class DoneCommand extends Command {

    private int indexToMarkDone = -1;

    public DoneCommand(int indexToMarkDone) {
        this.indexToMarkDone = indexToMarkDone;
    }

    /**
     * Overridden method. Executes the done command.
     * @param tasks list of tasks
     * @param ui user interface
     * @param storage storage file
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task toBeMarkDone = tasks.getTask(indexToMarkDone);
        toBeMarkDone.markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(toBeMarkDone);
        storage.updateChanges(tasks.getDukeTaskList());
    }

    @Override
    public String executeForGui(TaskList tasks, Ui ui, Storage storage) {
        Task toBeMarkDone = tasks.getTask(indexToMarkDone);
        toBeMarkDone.markAsDone();
        StringBuilder sb = new StringBuilder();
        sb.append("Nice! I've marked this task as done:");
        sb.append("\n");
        sb.append(toBeMarkDone);
        storage.updateChanges(tasks.getDukeTaskList());
        return sb.toString();
    }

}
