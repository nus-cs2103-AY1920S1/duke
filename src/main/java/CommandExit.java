public class CommandExit extends Command {

    private String string;

    public CommandExit(String fullCommand) {
        string = fullCommand;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printString("Bye. Hope to see you again soon!");
        this.isExit = true;
    }
}
