public class ByeCommand extends Command {
    private String command;
    private Ui ui;
    public ByeCommand(String command) {
        this.command = command;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws Exception {
        this.ui = ui;
    }

    public boolean isExit() {
        ui.printGoodbyeMsg();
        return true;
    }
}
