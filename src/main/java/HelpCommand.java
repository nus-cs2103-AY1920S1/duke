/**
 * Command to delete a task.
 */
public class HelpCommand extends Command {

    /**
     * Execute the delete command.
     *
     * @param listOfTasks the list of tasks to be added to
     * @param ui          The ui which displays the output
     * @param storage     to store the task
     */
    @Override
    public void execute(TaskList listOfTasks, Ui ui, Storage storage) {
        ui.println("todo <taskname> : Adds a todo task to the tasklist");
        ui.println("event <taskname> /by <location> : Adds an event task to the task list");
        ui.println("deadline <taskname> /at dd/mm/yyyy HHmm : Adds a deadline task to the task list");
        ui.println("list : Displays the task list");
        ui.println("done <index> : Marks a task at <index> as done");
        ui.println("delete <index> : Deletes a task at <index>");
        ui.println("find <keyword> : Displays all tasks with <keyword>");
        ui.println("bye : Exits the program");
    }

    /**
     * Method to know if it is an exit command.
     *
     * @return A boolean which states if it is an exit command
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
