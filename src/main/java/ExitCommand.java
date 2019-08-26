public class ExitCommand extends Command {

    public ExitCommand() {

    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printMessage("\n Bye. Hope to see you again soon! \n");
    }

    public boolean isExit() {
        return true;
    }
}
