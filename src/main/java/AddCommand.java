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
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        int taskListSize = tasks.getSize();
        tasks.addTask(this.task);
        try {
            storage.writeToHardDisk(tasks);
        } catch (DukeException e) {
            e.printStackTrace();
        }
        int taskListSizeAfterAddingTask = tasks.getSize();
        assert taskListSize + 1 == taskListSizeAfterAddingTask : "size of tasklist did not grow";
        return ui.addMessage(tasks, this.task);
    }

    /**
     * Boolean to exit from program
     * @return true or false depending if we want to stop the program
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
