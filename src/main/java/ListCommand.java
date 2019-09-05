public class ListCommand extends Command {

    /**
     * Returns whether the command is an ExitCommand.
     * @return whether the command is an ExitCommand
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Prints the list of tasks saved in memory.
     * @param tasks TaskList containing the user's saved tasks
     * @param ui Ui object to handle the user input
     * @param storage storage object to determine where the executed results are stored
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String reply = "Here are the tasks in your list:\n";
        int index = 0;
        for (Task t : tasks.tasks) {
            reply += (index + 1) + "." + t.toString() + "\n";
            index++;
        }
        return reply;
    }
}
