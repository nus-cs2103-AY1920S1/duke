public class AddCommand extends Command {
    private Task task;

    /**
     * Command for TaskList to be added with Task on execute.
     *
     * @param task Task to be added
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the command with given TaskList, Ui and Storage.
     *
     * @param tasks   TaskList
     * @param ui      Ui
     * @param storage Storage
     * @throws WriteFileFailDukeException On problems with Storage writing
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws WriteFileFailDukeException {
        tasks.add(task);
        storage.rewrite(tasks.getSerialized());
        ui.show("Got it. I've added this task:\n  "
            + task
            + "Now you have " + tasks.size() + " tasks in the list.");
    }
}
