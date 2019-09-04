/**
 * Encapsulates a command from user input String "bye".
 */
public class CommandExit extends Command {

    public CommandExit(String cmd) {
        super(cmd);
        super.type = "Exit: ";
    }

    @Override
    public void execute(Sheet sh, Ui ui, Storage stor) {
        ui.showGoodbye();
    }

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public String toString() {
        return "Exit: " + command;
    }
}
