public class ListCommand extends Command {
    public ListCommand() {
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {

        // Print all existing items in the list
        ui.printMessage("Here are the tasks in your list:");
        tasks.allTasks.forEach(x -> ui.printMessage((tasks.allTasks.indexOf(x) + 1) + ". " + x));
    }

    public boolean isExit() {
        return false;
    }
}
