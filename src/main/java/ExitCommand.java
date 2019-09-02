public class ExitCommand extends Command {
    public ExitCommand() {
        super("bye");
        this.isFinished = true;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showGoodbye();
    }
}

