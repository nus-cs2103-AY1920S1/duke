/**
 * Represents a DeleteCommand, an extension of the Command class. A <code>DeleteCommand</code>
 * object corresponds to a type and itemNo represented by two strings.
 */
public class DeleteCommand extends Command {
    protected String itemNo;

    public DeleteCommand(String type, String itemNo) {
        super(type);
        this.itemNo = itemNo;
    }

    /**
     * Removes task from list based on index given.
     * @param tasks Class dealing with arraylist of tasks
     * @param ui Class dealing with User interface
     * @param storage Class dealing with storage of task list
     * @return Output message to console.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        int item = Integer.parseInt(itemNo) - 1;
        int num = tasks.list.size();
        assert item < num;

        String line1 = "Noted. I've removed this task:\n" + "   " + tasks.list.get(item);

        tasks.list.remove(item);
        assert tasks.list.size() == num - 1;

        String line2 = "Now you have " + tasks.list.size() + " in the list.";

        return line1 + "\n" + line2;
    }
}
