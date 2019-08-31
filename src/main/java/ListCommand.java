public class ListCommand extends Command {

    @Override
    public void execute(Storage storage, TaskList tasks, Ui ui) {
        tasks.showTaskList();
    }
}
