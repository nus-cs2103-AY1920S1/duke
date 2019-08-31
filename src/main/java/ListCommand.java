public class ListCommand extends Command {
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.list();
    }
    public boolean isExit() {
        return false;
    }
}