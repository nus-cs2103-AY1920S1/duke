public class ListCommand extends Command {
    /**
     * Prints the items in this TaskList.
     *
     * @param taskList The TaskList object passed from Duke.
     * @param ui The Ui object passed from Duke.
     * @param storage The Storage object passed from Duke.
     * @return The response String.
     * @throws DukeException A DukeException custom exception.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (taskList.getSize() == 0) {
            throw new EmptyTaskListException("OOPS!!! You have no tasks currently stored in your list!");
        }
        String response = "Here are the tasks in your list:";
        for (int i = 0; i < taskList.getSize(); i++) {
            response = response.concat("\n" + (i + 1) + ". " + taskList.getTask(i));
        }
        return response;
    }
}
