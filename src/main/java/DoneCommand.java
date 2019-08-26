public class DoneCommand extends Command {

    public DoneCommand(String fullCommand, String[] splitCommand) {
        super(fullCommand, splitCommand);
    }

    @Override
    public void execute(ListManager listManager, Ui ui, Storage storage) {
        int index = Integer.parseInt(splitCommand[1]);
        listManager.done(index);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

