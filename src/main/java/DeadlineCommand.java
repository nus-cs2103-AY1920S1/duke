public class DeadlineCommand implements Command {
    private String task;
    private String time;

    public DeadlineCommand(String task, String time) {
        this.task = task;
        this.time = time;
    }

    public void execute(Storage storage, Ui ui, TaskList tasks) {
        Deadline dl = new Deadline(task, time);
        tasks.addTask(dl);
        ui.output(String.format("Got it. I've added this task:\n  %s\nNow you have %d tasks in the list",
                dl.toString(), tasks.getTasksSize()));
    }

    public boolean isRunning() {
        return true;
    }
}
