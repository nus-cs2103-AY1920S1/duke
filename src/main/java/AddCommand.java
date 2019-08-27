public class AddCommand extends Command {

    private Task taskToAdd;

    public AddCommand(Task task) {
        this.taskToAdd = task;
    }
    public void execute(TaskList tasks) throws DukeException {
        tasks.addTask(this.taskToAdd);
    }
}
