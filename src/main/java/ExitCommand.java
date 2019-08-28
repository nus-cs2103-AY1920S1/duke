public class ExitCommand extends Command{

    public ExitCommand() {
        super(true);
    }

    @Override
    public void execute(Storage storage, TaskList tasks, UI ui) {
        ui.exit();
    }

}
