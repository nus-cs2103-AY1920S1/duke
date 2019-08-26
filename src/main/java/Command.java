public abstract class Command {
    private Task task;
    public Command(Task task) {
        this.task = task;
    }

    public abstract void execute(TaskList tasklist);
}
