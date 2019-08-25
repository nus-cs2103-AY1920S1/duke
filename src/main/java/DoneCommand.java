public class DoneCommand extends Command{
    int id;
    public DoneCommand(int i) {
        this.id = i;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.completeTask(this.id);
        ui.showComplete(tasks.getTask(this.id));
    }
}
