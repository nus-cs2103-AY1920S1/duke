public class ListCommand extends Command {

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.printTasks();
    }
}
