public class DeleteCommand extends Command {

    public DeleteCommand(String fullCommand, String[] splitCommand) {
        super(fullCommand, splitCommand);
    }

    @Override
    public void execute(ListManager listManager, Ui ui, Storage storage) {
        int index = Integer.parseInt(splitCommand[1]);
        listManager.delete(index);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

