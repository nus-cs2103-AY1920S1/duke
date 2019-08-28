public class TodoCommand implements Command {
    private String task;

    public TodoCommand(String task) {
        this.task = task;
    }

    public void execute(Storage storage, Ui ui, TaskList tasks) {
        Todo td = new Todo(task);
        tasks.addTask(td);
        ui.output(String.format("Got it. I've added this task:\n  %s\nNow you have %d tasks in the list",
                td.toString(), tasks.getTasksSize()));
    }

    public boolean isRunning() {
        return true;
    }
}
