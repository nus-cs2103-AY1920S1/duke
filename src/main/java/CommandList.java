/**
 * Represents the list command to list the task(s) in the task list.
 */
public class CommandList extends Command {

    /**
     * Executes the list command.
     * @param tasks the task list
     * @param ui the UI
     * @param storage the storage writer
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printString("Here are the tasks in your list:");
        int count = 1;
        for (Task t: tasks.getList()) {
            String tempStr = count + "." + t.toString();
            ui.printString(tempStr);
            count++;
        }

    }
}
