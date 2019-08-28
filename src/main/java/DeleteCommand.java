public class DeleteCommand extends Command {

    private int idxToBeRemoved;

    public DeleteCommand(int idxToBeRemoved) {
        this.idxToBeRemoved = idxToBeRemoved;
    }

    @Override
    boolean isExit() {
        return false;
    }

    /**
     * execute() will remove Task from TaskList.
     * @param tasks is the TaskList
     * @param ui is the Ui
     * @param storage is the Storage
     */
    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) {
        Task taskToBeRemoved = tasks.getTask(idxToBeRemoved);
        tasks.removeTask(taskToBeRemoved);
        ui.printDelete(taskToBeRemoved, tasks.getSize());
    }
}
