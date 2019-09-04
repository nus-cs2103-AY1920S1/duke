/**
 * Encapsulates a command from user input String "clear".
 */
public class CommandClear extends Command {

    public CommandClear(String command) {
        super(command);
    }

    @Override
    public void execute(Sheet sh, Ui ui, Storage stor) throws DukeException {
        sh.clearList();
        stor.save(sh.getList());
    }

    @Override
    public String toString() {
        return "Clear: " + command;
    }
}
