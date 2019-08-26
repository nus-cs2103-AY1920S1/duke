public class ShowCommand extends Command {

    public ShowCommand(String cmd) {
        super(cmd);
    }

    public void execute(TaskList tasks, UI ui, Storage storage) {
        ui.showList(tasks);
    }

    public boolean isExit() {
        return false;
    }
}
