public class ByeCommand extends Command {

    public ByeCommand() {
        super.isExit = true;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        super.execute(tasks, ui, storage);
        ui.bye();
    }
}