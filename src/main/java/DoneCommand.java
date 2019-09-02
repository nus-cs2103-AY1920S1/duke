public class DoneCommand extends Command{

    private int index;

    /**
     * Creates a DoneCommand object
     * @param index of the task to be marked as done
     */
    public DoneCommand(int index) {
        super();
        this.index = index;
    }

    /**
     * Executes the command.
     * Marks the specified task as done.
     * @param tasks to handle the tasks
     * @param ui to get user input
     * @param storage to store tasks
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.getTask(this.index);
        task.markAsDone();
        try {
            storage.writeToHardDisk(tasks);
        } catch (DukeException e) {
            e.printStackTrace();
        }
        ui.printDoneMessage(task);
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
