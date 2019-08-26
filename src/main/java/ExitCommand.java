public class ExitCommand extends Command {

    public ExitCommand() {

    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showLine("Bye. Hope to see you again soon!");
    }

    public boolean isExit() {
        return true;
    }
}
