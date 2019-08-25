public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.add(task);
        storage.rewrite(tasks.getSerialized());
        ui.show("Got it. I've added this task:\n  " +
                task +
                "Now you have " + tasks.size() + " tasks in the list.");
    }
}
