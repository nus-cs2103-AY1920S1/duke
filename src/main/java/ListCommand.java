public class ListCommand extends Command{
    private int StartNumber;

    public ListCommand(){
        this.StartNumber = 1;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.listAllTasks(ui);
    }
}
