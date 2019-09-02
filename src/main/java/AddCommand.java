public class AddCommand extends Command {
    private Task task;
    /**
     * Creates an AddCommand object
     * Adds the task to the tasklist
     * @param task
     */
    public AddCommand(Task task) {
        super();
        this.task = task;
    }
    /**
     * Executes the command.
     * Adds the task to the tasklist.
     * @param tasks to handle the tasks
     * @param ui to get user input
     * @param storage to store tasks
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(task);
        ui.printAddMessage();
        try {
            storage.writeToHardDisk(tasks);
        } catch (DukeException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
