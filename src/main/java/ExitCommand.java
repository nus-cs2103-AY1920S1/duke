public class ExitCommand extends Command {

    public ExitCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        storage.save(tasks.getTasks());
        ui.showFarewell();
    }

    public boolean isExit() {
        return true;
    }
}
