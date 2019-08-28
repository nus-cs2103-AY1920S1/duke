public class ListCommand extends Command {

    @Override
    boolean isExit() {
        return false;
    }

    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printList(tasks.getTaskList());
    }
}
