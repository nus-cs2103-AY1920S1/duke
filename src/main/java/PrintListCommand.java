/**
 * A Command to print the task list.
 */
class PrintListCommand extends Command {

    /**
     * Executes the command to print the task list.
     *
     * @param tasks The task list.
     * @param storage The storage that handles saving and loading the task list.
     */
    @Override
    String execute(TaskList tasks, Storage storage) {
        if (tasks.isEmpty()) {
            return "There are currently no tasks in your list!";
        }
        StringBuilder sb = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 1; i <= tasks.size(); i++) {
            sb.append(String.format("%d. %s\n", i, tasks.get(i - 1)));
        }
        return sb.toString().trim();
    }

}
