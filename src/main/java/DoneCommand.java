public class DoneCommand extends Command {
    private final int index;

    /**
     * Initialises a DoneCommand.
     *
     * @param index
     */
    public DoneCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the Done Command and marks the task as done.
     *
     * @param tasks   The TaskList containing all existing tasks.
     * @param ui      The Ui for printing purposes.
     * @param storage The Storage for saving tasks to file.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {

        // Delete from file, mark as done, reinsert into file
        storage.deleteTaskFromFile(tasks.allTasks.get(index));
        tasks.allTasks.get(index).markAsDone();
        ui.printMessage("Nice! I've marked this task as done:\n" + tasks.allTasks.get(index));
        storage.appendTaskToFile(tasks.allTasks.get(index));
    }

    /**
     * Checks if it is a bye command.
     *
     * @return A boolean: true if it is a bye command.
     */
    public boolean isExit() {
        return false;
    }
}
