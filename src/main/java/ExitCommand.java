public class ExitCommand extends Command {

    @Override
    public void execute(Storage storage, TaskList tasks, Ui ui) throws DukeException {
        hasExit = true;
        storage.saveData(tasks.getTaskList());
        ui.showGoodbye();
    }
}
