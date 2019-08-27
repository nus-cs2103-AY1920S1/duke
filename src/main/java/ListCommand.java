public class ListCommand extends Command {

    public void execute(TaskList tasks, UI ui, Storage storage) {
        ui.showMessage(tasks.list());
    }

    public boolean isExit() {
        return false;
    }

}