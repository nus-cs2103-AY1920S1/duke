public class DoneCommand extends Command {
    private int idxToMarkAsDone;

    /**
     * Constructs a Command to mark done a Task from TaskList.
     *
     * @param idxToMarkAsDone the index of the Task to be marked as done.
     */
    public DoneCommand(int idxToMarkAsDone) {
        this.idxToMarkAsDone = idxToMarkAsDone;
    }

    @Override
    boolean isExit() {
        return false;
    }

    /**
     * Executes an DoneCommand given TaskList, UI, Storage.
     *
     * @param tasks the TaskList.
     * @param ui the UI.
     * @param storage the file storage.
     */
    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task taskToMarkAsDone = tasks.getTask(this.idxToMarkAsDone);
            taskToMarkAsDone.markAsDone();
            ui.printDone(taskToMarkAsDone);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeIllegalArgumentException("Invalid input. Please input a valid number between 1 and "
                    + tasks.getSize());
        }
    }
}
