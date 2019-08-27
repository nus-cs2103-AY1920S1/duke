public class ListCommand extends Command {

    public void execute(TaskList tasks, UI ui, Storage storage) {
        ui.showMessage(ui.padMessage(tasks.getTasks()));
    }

    public boolean isExit() {
        return false;
    }

}