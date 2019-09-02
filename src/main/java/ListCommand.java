public class ListCommand extends Command {
    public ListCommand() {
        super("list");
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (taskList.getNumTasks() == 0) {
            ui.showMessage("You have no tasks yet!");
        } else {
            ui.showAllTasks(taskList);
        }
    }
}