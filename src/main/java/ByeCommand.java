public class ByeCommand extends Command {

    public void execute(TaskList tasks, UI ui) {
        ui.showByeMessage();
    }

    public boolean isExit() {
        return true;
    }

}