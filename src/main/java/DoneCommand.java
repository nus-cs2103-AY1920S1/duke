public class DoneCommand extends Command {
    private int taskNumToComplete;

    public DoneCommand(int taskNumToComplete){
        super();
        this.taskNumToComplete = taskNumToComplete;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (this.taskNumToComplete <= 0 || taskNumToComplete > tasks.getSize()) {
            throw new DukeException("Task Number is out of bounds");
        }
        Task taskToComplete = tasks.markTaskAsDone(this.taskNumToComplete);
        ui.messageUser("Nice! I've marked this task as done:",
                "  " + taskToComplete.getTaskStatus());
    }
}
