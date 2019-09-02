public class ListCommand extends Command {

    public ListCommand() {
        super();
    }

    /**
     * Executes the command.
     * Lists all files in the task.
     * @param tasks to handle the tasks
     * @param ui to get user input
     * @param storage to store tasks
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println("Here are the tasks in your list:");
        tasks.showList();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
