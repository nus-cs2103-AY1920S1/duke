public abstract class NewTaskCommand extends Command {
    private Task task;

    public NewTaskCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute() throws DukeException {
        this.taskList.add(this.task);
        this.ui.displayMessage("Got it. I've added this task:\n  "
                + task
                + "\nNow you have "
                + this.taskList.getSize()
                + " task(s) in the list.");
        this.storage.saveToDisk(this.taskList);
    }
}
