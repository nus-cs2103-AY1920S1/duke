public class ClearAllTasksCommand extends Command {
    public ClearAllTasksCommand() {
        super();
    }
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        storage.clearAll();
        ui.dukeEcho("All tasks have been cleared. You now have an empty task list!");
        tasks.clearAll();
    }
}
