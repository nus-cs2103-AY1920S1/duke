public class DeleteCommand extends Command {
    private final int index;

    /**
     * Initialises a DeleteCommand.
     *
     * @param index The index of the task to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the Delete Command and removes the task from the Linkedlist and file.
     *
     * @param tasks   The TaskList containing all existing tasks.
     * @param ui      The Ui for printing purposes.
     * @param storage The Storage for saving tasks to file.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {

        StringBuilder sb = new StringBuilder("Noted. I've removed this task:\n" + tasks.allTasks.get(index));
        storage.deleteTaskFromFile(tasks.allTasks.get(index));
        tasks.allTasks.remove(index);
        if (tasks.allTasks.size() == 1) {
            sb.append("\nNow you have ").append(tasks.allTasks.size()).append(" task in the list.");
        } else {
            sb.append("\nNow you have ").append(tasks.allTasks.size()).append(" tasks in the list.");
        }
        ui.printMessage(sb.toString());
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
