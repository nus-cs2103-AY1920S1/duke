/**
 * Represents an "add" command that allows the user to add one of the possible types of task.
 */
abstract class AddCommand extends Command {
    public AddCommand(String input) {
        super.input = input;
    }

    /**
     * Adds a new task to the storage and TaskList and gives notice once completed.
     *
     * @param tasks   The taskList used by the current Duke app.
     * @param storage The Storage unit used for the current Duke app.
     * @param newTask The new task that will be added.
     */
    public String addTask(TaskList tasks, Storage storage, Task newTask) {
        tasks.add(newTask);
        String output = "Got it. I've added this task:\n";
        output += "    " + newTask.toString() + "\n";
        output += "Now you have " + tasks.size() + " tasks in the list.";
        storage.writeList(tasks);
        return output;
    }

}
