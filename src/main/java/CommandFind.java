/**
 * Represents the find command to find specific tasks.
 */
public class CommandFind extends Command {

    /**
     * The keyword(s) of the task.
     */
    private String string;

    /**
     * Constructs the find command.
     * @param str keyword(s)
     */
    public CommandFind(String str) {
        this.string = str;
    }

    /**
     * Executes the find command.
     * @param tasks the task list
     * @param ui the UI
     * @param storage the storage writer
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printString("Here are the matching tasks in your list:");
        int count = 1;
        for (Task t: tasks.getList()) {
            if (t.getDescription().contains(string)) {
                String tempStr = count + "." + t.toString();
                ui.printString(tempStr);
                count++;
            } else {

            }
        }
    }
}
