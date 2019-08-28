public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            storage.saveTasks(tasks);
        } catch (DukeException e) {
            ui.printError(e);
        }

        ui.printExit();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
