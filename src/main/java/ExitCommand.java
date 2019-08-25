public class ExitCommand extends Command {
    public ExitCommand() {
        super();
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException{
        storage.updateSaveFile(tasks.getAllTasks());
        ui.showGoodbye();
    }

    @Override
    public boolean isExit() {
        return true;
    }

}
