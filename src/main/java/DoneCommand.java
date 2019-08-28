public class DoneCommand extends Command {

    private int taskID;
    public DoneCommand(int taskID) {
        super();
        this.taskID = taskID;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (this.taskID > tasks.size()) {
            throw new DukeException("Invalid ask ID. Please Enter a task ID between 1 and " + tasks.size());
        }
        Task completedTask = tasks.finishTask(taskID);
        ui.dukeEcho("Nice! I've marked this task as done:", completedTask.toString());
        storage.save(tasks);
    }
}
