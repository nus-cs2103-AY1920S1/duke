public class ByeCommand extends Command {
    ByeCommand() {
        super();
    }

    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui) {

    }

    @Override
    public boolean isBye() {
        return true;
    }
}
