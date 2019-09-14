/**
 * A Command to exit the Duke program.
 */
class ExitCommand extends Command {

    /**
     * Executes the command to exit the Duke program.
     *
     * @param tasks The task list.
     * @param storage The storage that handles saving and loading the task list.
     */
    @Override
    String execute(TaskList tasks, Storage storage) {
        return "Bye. Hope to see you again soon!";
    }

}
