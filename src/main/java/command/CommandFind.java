package command;

import command.Command;
import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

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

    /**
     * Executes the find command.
     * @param tasks the task list
     * @param storage the storage writer
     * @return String
     */
    @Override
    public String executeForGUI(TaskList tasks, Storage storage) {
        String curr = "Here are the matching tasks in your list:";
        int count = 1;
        for (Task t: tasks.getList()) {
            if (t.getDescription().contains(string)) {
                curr = curr + "\n" + count + "." + t.toString();
                count++;
            } else {

            }
        }
        return curr;
    }
}
