public class DeleteCommand extends Command {

    private int idxToBeRemoved;

    /**
     * Constructs a Command to delete a specific Task from TaskList.
     *
     * @param idxToBeRemoved the index of the Task to be removed.
     */
    public DeleteCommand(int idxToBeRemoved) {
        this.idxToBeRemoved = idxToBeRemoved;
    }

    @Override
    boolean isExit() {
        return false;
    }

    /**
     * Executes an DeleteCommand given TaskList, UI, Storage.
     *
     * @param tasks the TaskList.
     * @param ui the UI.
     * @param storage the file storage.
     */
    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task taskToBeRemoved = tasks.getTask(idxToBeRemoved);
            tasks.removeTask(taskToBeRemoved);
            ui.printDelete(taskToBeRemoved, tasks.getSize());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeIllegalArgumentException("Invalid input. Please input a valid number between 1 and "
                    + tasks.getSize());
        }
    }
}
