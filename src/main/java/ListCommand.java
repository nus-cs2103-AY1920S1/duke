public class ListCommand extends Command {
    public ListCommand() {
        super.isExit = false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.listTasks(ui);
    }
}
