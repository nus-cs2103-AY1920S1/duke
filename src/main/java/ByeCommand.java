public class ByeCommand extends Command {
    public ByeCommand() {

    }

    public boolean isExit() {
        return true;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage("Bye! See you again soon!!");
    }
}
