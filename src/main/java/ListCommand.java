public class ListCommand extends Command {
    /**
     * ListCommand constructor.
     * @param input The input task enter by user.
     * @param type The type of task enter by user.
     */
    public ListCommand(String input, String type) {
        super(input, type);
    }

    /**
     * Execute method to execute the list method.
     * @param tasks List of task.
     * @param ui The Ui of the Duke Program.
     * @param storage The Database of the Duke Program.
     * @throws DukeException The list method is not able to execute.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
      list(tasks, ui);
    }

    /**
     * "list" command to list all the task.
     * @throws DukeException if number of items = 0.
     */
    public static void list(TaskList tasks, Ui ui) throws DukeException {
        if (tasks.getItemNo() == 0) {
            throw new DukeException(ui.INDENT_COMMENT + "\u2639 OOPS !!! " + "The task list are currently empty.");
        }
        int index = 1;
        System.out.println(ui.INDENT_COMMENT + "Here are the tasks in your list:");
        for (int i = 0; i < tasks.getTask().size(); i++) {
            System.out.println("    " + index++ + "." + tasks.getTask().get(i));
        }
    }
}