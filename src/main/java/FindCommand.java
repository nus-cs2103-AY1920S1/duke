public class FindCommand extends Command {
    public FindCommand(String input) {
        super(input);
    }

    /**
     * Executes the command to find a task.
     *
     * @param tasks   current list of tasks.
     * @param ui      Ui object.
     * @param storage Storage object to save and load files.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            String desc = this.input.substring(4).trim();
            StringBuilder result = new StringBuilder();
            int i = 1;
            for (Task task : tasks.getTasks()) {
                if (task.contains(desc)) {
                    result.append("     " + i + "." + task + "\n");
                    i++;
                }
            }
            Duke.print("Here are the matching tasks in your list:" + "\n" + result);
            return ("Here are the matching tasks in your list:" + "\n" + result);
        } catch (Exception e) {
            Duke.print("☹ OOPS!!! The description of a find cannot be empty.");
            return("☹ OOPS!!! The description of a find cannot be empty.");
        }
    }
}
