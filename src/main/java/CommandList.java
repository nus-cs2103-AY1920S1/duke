/**
 * Encapsulates a command from user input String "list".
 */
public class CommandList extends Command {

    public CommandList(String command) {
        super(command);
        super.type = "List: ";
    }

    @Override
    public void execute(Sheet sh, Ui ui, Storage stor) {
        sh.showList();
    }

    @Override
    public String toString() {
        return "List: " + command;
    }
}
