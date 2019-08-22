import java.util.List;

class ListCommand implements Command {
    public void execute(List<Task> tasks, Ui ui, Storage storage) {
        ui.displayTasks("Here are the tasks in your list:", tasks);
    }
}
