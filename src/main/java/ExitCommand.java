public class ExitCommand extends Command {
    public ExitCommand(String commandString) {
        super(commandString);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        this.setExit(true);
        ui.showBye();
    }
}
