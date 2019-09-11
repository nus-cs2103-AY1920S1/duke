public class ExitCommand extends Command {

    public ExitCommand() {
        super("bye");
    }

    public void execute(Ui ui) {
        ui.showGoodbye();
    }
}

