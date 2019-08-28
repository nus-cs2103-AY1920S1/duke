public class ExitCommand extends Command {

    @Override
    public void execute(MyList taskList, UserInterface ui, Storage storage) {
        ui.printExitMsg();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
