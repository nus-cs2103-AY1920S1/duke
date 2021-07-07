public class DoneCommand extends Command{

    private int index;

    /**
     * Creates a DoneCommand object
     * @param index of the task to be marked as done
     */
    public DoneCommand(int index) {
        super();
        this.index = index - 1;
    }

    /**
     * Executes the command.
     * Marks the specified task as done.
     * @param tasks to handle the tasks
     * @param ui to get user input
     * @param storage to store tasks
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.getTask(this.index);
        task.markAsDone();
        try {
            storage.writeToExternalTextFile(tasks);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
        return ui.doneMessage(task);
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
