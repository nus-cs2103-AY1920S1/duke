public class IllegalCommand extends Command {

    public void execute(TaskList tasks, UI ui) {
        ui.showIllegalCommandMessage();
    }

    public boolean isExit() {
        return false;
    }

}