public class ListCommand extends Command {
    ListCommand() {
        super();
    }

    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui) {
        ui.showTaskList(taskList);
    }
}
