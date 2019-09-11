public class ExitCommand extends Command {
    public ExitCommand() {
        super("bye");
        this.isFinished = true;
    }

    // NEW (remove return)
    public String execute(TaskList taskList, Ui ui, Storage storage) {
    //public void execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.showGoodbye();
    }
}

