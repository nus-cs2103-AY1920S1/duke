public class ByeCommand extends Command {
    public void execute(Ui ui, Storage storage, TaskList allTasks) throws DukeException {
        this.isExit = true;
        super.execute(ui, storage, allTasks);
    }
}
