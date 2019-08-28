/**
 * Represents a command to print out the tasks in the arraylist.
 */

public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println("Here are the tasks in your list:");
        int listCount = 1;
        for (Task task : tasks.getList()) {
            System.out.println(listCount + "." + task);
            listCount++;
        }
    }
}
