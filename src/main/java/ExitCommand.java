public class ExitCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException{
        storage.save(taskList.tasksToStringList(true));
        ui.showExit();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
