/**
 * Represents a command which changes Task specified in an index in the Tasklist to be done.
 * @see TaskList
 * @see Task
 */

public class DoneCommand extends Command {
    String[] commandSplit = super.stringCommand.split(" ");

    /**
     * Constructor for DoneCommand.
     * @param stringCommand String representation of the user input
     */
    public DoneCommand(String stringCommand) {
        super(stringCommand);
    }

    /**
     * Executes the command by using the three arguments provided.
     * @param taskList
     * @param ui
     * @param storage
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        int index = Integer.parseInt(commandSplit[1]);
        ui.printDoneMessage();
        taskList.done(index);
        ui.printTask(taskList.getTasks().get(index-1));
    }

    /**
     * Checks if Duke will end.
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
