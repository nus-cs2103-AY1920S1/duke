public class DeleteCommand extends Command {
    private int index;
    /**
     * Creates an DeleteCommand object
     * Deletes a task from the tasklist at a certain index
     * @param task to be deleted
     */
    public DeleteCommand(int index) {
        super();
        this.index = index - 1;
    }

    /**
     * Executes the command.
     * Deletes the specified task from the tasklist.
     * @param tasks to handle the tasks
     * @param ui to get user input
     * @param storage to store tasks
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.getTask(this.index);
        int taskListSize = tasks.getSize();
        tasks.delete(task);
        try {
            storage.writeToExternalTextFile(tasks);
        } catch (DukeException e) {
            e.printStackTrace();
        }
        int taskListSizeAfterDeletingTask = tasks.getSize();
        assert taskListSize - 1 == taskListSizeAfterDeletingTask
                : "task list did not shrink by 1 after deletion";
        return ui.deleteMessage(tasks, task);
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
