public class ExitCommand extends Command {
    public ExitCommand(String command, String commandDetails, String INDENT) {
        super(command, commandDetails, INDENT);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.exit();
    }
}
