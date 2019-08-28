public class ListCommand extends Command {

    /**
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
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println("Here are the tasks in your list:");
        int index = 0;
        for (Task t : tasks.tasks) {
            System.out.println((index + 1) + "." + t.toString());
            index++;
        }
    }
}
