public class ListCommand extends Command {
    public ListCommand() {
        super("list");
    }

    // NEW
    public String execute(TaskList taskList, Ui ui, Storage storage) {
    //public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (taskList.getNumTasks() == 0) {
            return ui.showMessage("You have no tasks yet!");
        } else {
            return ui.showAllTasks(taskList);
        }
    }
}