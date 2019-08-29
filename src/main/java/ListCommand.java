public class ListCommand extends Command {
    /**
     * Constructor for AddCommand
     * @param stringCommand
     */
    public ListCommand(String stringCommand) {
        super(stringCommand);
    }

    /**
     * Executes the command by using the three arguments provided
     * @param taskList
     * @param ui
     * @param storage
     */
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        ui.printListMessage();
        ui.printTaskList(taskList);
        taskList.list();
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
