public class ExitCommand extends Command {

    public ExitCommand(String command) {
        super(command);
    }

    public void execute(TaskList tasks, UI ui, Storage storage) throws DukeException {
        ui.showExitMessage();
        storage.save(tasks);
    }

    public boolean isExit() {
        return true;
    }
}
