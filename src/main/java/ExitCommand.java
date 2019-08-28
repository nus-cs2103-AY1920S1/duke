public class ExitCommand extends Command {

    public ExitCommand(String inputCommand) {
        super(inputCommand);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        this.shouldExit = true;
        ui.printBye();
    }
}
