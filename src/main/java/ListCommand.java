/**
 * List Command. Command to print out the tasklist.
 */
public class ListCommand extends Command {

    /**
     * Constructor. Sets isExit to false as it is not an exit command.
     */
    public ListCommand() {
        isExit = false;
    }

    /**
     * Behaviour of List Command. Prints out tasklist.
     * @param taskList TaskList of tasks.
     * @param ui Ui object for user interaction.
     * @param storage Storage object for data file.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        System.out.println(taskList);
    }
}
