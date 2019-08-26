public class ListCommand extends Command {

    public ListCommand(String fullCommand, String[] splitCommand) {
        super(fullCommand, splitCommand);
    }

    @Override
    public void execute(ListManager listManager, Ui ui, Storage storage) {
        listManager.iterate();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
