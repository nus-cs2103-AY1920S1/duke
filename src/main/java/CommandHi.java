/**
 * Encapsulates a command from user input String "hi".
 */
public class CommandHi extends Command {

    public CommandHi(String command) {
        super(command);
    }

    @Override
    public void execute(Sheet sh, Ui ui, Storage stor) {
        ui.sayHi();
    }

    @Override
    public String toString() {
        return "Hi: " + command;
    }
}
