public class ExitCommand extends Command {

    public ExitCommand() {
        super();
        super.isExit = true;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.printLine("Bye. Hope to see you again soon!");
    }
}