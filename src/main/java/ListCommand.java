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
     * @return message response to user
     */
    @Override
    public String executeAndReturnMessage(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.getNumOfTasks() == 0) {
            return "You don't have any tasks to do! Get a life!";
        }

        String reply = "Here are the tasks in your list:\n";
        int index = 0;
        for (Task t : tasks.tasks) {
            reply += (index + 1) + "." + t.toString() + "\n";
            index++;
        }
        return reply;
    }
}
