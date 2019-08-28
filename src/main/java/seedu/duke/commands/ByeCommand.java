public class ByeCommand extends Command {

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        ui.bye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
