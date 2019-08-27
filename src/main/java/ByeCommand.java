public class ByeCommand extends Command {

    public void execute(TaskList tasks, UI ui, Storage storage) {
        ui.showByeMessage();
    }

    public boolean isExit() {
        return true;
    }

}