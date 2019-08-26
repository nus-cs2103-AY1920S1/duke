public class ListCommand extends Command {

    public ListCommand() {

    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showLine("Here are the tasks in your list:");

        for (int i = 0; i < tasks.size(); i++) {
            ui.showLine((i + 1) + "." + tasks.get(i).toString());
        }
    }

    public boolean isExit() {
        return false;
    }

}
