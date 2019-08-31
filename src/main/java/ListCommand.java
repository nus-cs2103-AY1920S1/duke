public class ListCommand implements Command {

    public void execute(TaskList task, Ui ui, Storage storage) {
        ui.showTable(task.list());
    }

    public boolean isExit() {
        return false;
    }

}
