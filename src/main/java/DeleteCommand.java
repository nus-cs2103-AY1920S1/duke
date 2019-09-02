public class DeleteCommand extends Command {
    private int index;
    /**
     * Creates an DeleteCommand object
     * Deletes a task from the tasklist at a certain index
     * @param task
     */
    public DeleteCommand(int index) {
        super();
        this.index = index;
    }

    /**
     * Executes the command.
     * Deletes the specified task from the tasklist.
     * @param tasks to handle the tasks
     * @param ui to get user input
     * @param storage to store tasks
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.getTask(this.index);
        tasks.delete(task);
        try {
            storage.writeToHardDisk(tasks);
        } catch (DukeException e) {
            e.printStackTrace();
        }
        ui.printDeleteMessage();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
