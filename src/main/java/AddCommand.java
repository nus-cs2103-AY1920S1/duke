/**
 * Represents the command to add a task into the arraylist.
 */


public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.getList().add(task);
        storage.writeFile(tasks.getList());
        return toString() + ui.printTask(tasks.getList().size(), task);
    }

    @Override
    public String toString() {
        return "Got it. I've added this task:\n";
    }
}
