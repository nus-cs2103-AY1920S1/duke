public class IllegalCommand extends Command {

    public void execute(TaskList tasks, UI ui, Storage storage) {
        ui.showIllegalCommandMessage();
    }

    public boolean isExit() {
        return false;
    }

}