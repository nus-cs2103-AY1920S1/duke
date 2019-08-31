public class doneCommand implements Command{
    private int doneIndex;
    boolean isExist = false;

    doneCommand(int doneIndex) {
        this.doneIndex = doneIndex;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task doneTask = tasks.doneTask(doneIndex);
        ui.showDone(doneTask);
        storage.saveFile(tasks.getList());
    }
}
