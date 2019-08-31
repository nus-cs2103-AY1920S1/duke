/**
 * Represents an "add" command that allows the user to add one of the possible types of task.
 */
abstract class AddCommand extends Command {
    /**
     * Adds a new task to the storage and TaskList and gives notice once completed.
     * @param tasks The taskList used by the current Duke app.
     * @param ui The Ui responsible for displaying things in the current Duke app.
     * @param storage The Storage unit used for the current Duke app.
     * @param newTask The new task that will be added.
     */
    public void addTask(TaskList tasks, Ui ui, Storage storage, Task newTask) {
        tasks.add(newTask);
        ui.showMessage("Got it. I've added this task:");
        ui.showMessage(6, newTask.toString());
        ui.showMessage("Now you have " + tasks.size() + " tasks in the list.");
        storage.writeList(tasks);
    }

}
