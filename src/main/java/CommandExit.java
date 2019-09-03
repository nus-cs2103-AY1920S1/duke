public class CommandExit extends Command{

    public CommandExit(String cmd) {
        super(cmd);
    }

    @Override
    public void execute(Sheet sh, Ui ui, Storage stor) {
        ui.showGoodbye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
