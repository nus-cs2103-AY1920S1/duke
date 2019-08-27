public abstract class Command {
    private Task task;
    /**
     * Constructor
     * @param task - Task given to execute command
     */
    public Command(Task task) { this.task = task; }

    /**
     * Execute command on given task and save into tasklist
     */
    public abstract void execute(TaskList taskList);
}
