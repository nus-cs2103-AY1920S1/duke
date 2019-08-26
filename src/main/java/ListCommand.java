public class ListCommand extends Command {

    public ListCommand() {

    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showLine("Here are the tasks in your list:");

        ui.showLine(tasks.printTaskList());
    }

    public boolean isExit() {
        return false;
    }

}
