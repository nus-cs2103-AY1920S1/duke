public class DoneCommand extends Command{
    /**
     * DoneCommand class constructor.
     * @param input The input task enter by user.
     * @param type The type of task.
     */
    public DoneCommand(String input, String type) {
        super(input,type);
    }

    /**
     * The execute method to execute done method.
     * @param tasks The list of task.
     * @param ui The ui of Duke Program.
     * @param storage The Database of Duke Program.
     * @throws DukeException If done method is not executed properly.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        done(super.input, tasks, ui);
    }

    /*
     * "done" command to check the finish task.
     * @param data Command and item index of the task.
     * @throws DukeException if number of items = 0 and index enter > total number of task.
     */
    public static void done(String data, TaskList tasks, Ui ui) throws DukeException {
        try {
            if (data.isEmpty()) {
                if (tasks.getItemNo() == 0) {
                    throw new DukeException(ui.INDENT_COMMENT + "\u2639 OOPS !!! " + "The task list are currently empty.");
                } else {
                    throw new DukeException(ui.INDENT_COMMENT + "\u2639 OOPS !!! " + "Index of task are needed.");
                }
            }

            int item = Integer.parseInt(data);

            if (item > tasks.getItemNo()) {
                if (tasks.getItemNo() == 0) {
                    throw new DukeException(ui.INDENT_COMMENT + "\u2639 OOPS !!! " + "The task list are currently empty.");
                } else {
                    throw new DukeException(ui.INDENT_COMMENT + "\u2639 OOPS !!! " + "Number enter can only be less than or equal number of task.");
                }
            }

            System.out.println(ui.INDENT_COMMENT + "Nice! I've marked this task as done:");
            Task t = tasks.getTask().get(--item);
            t.markAsDone();
            System.out.println(ui.INDENT_TASK + t);
        } catch (NumberFormatException ex) {
            System.out.println(ui.INDENT_COMMENT + "\u2639 OOPS !!! " + "Only Integer is allowed after done.");
        }
    }
}