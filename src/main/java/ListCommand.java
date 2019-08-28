public class ListCommand extends Command {

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.showLine();
        ui.showList(tasks);
        ui.showLine();
    }

    public boolean isExit() {
        return false;
    }
}