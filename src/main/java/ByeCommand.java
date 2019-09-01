public class ByeCommand extends Command{
    /**
     * Constructor for ByeCommand
     * @param stringCommand String representation of the user input
     */
    public ByeCommand(String stringCommand){
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
        ui.bye();
        storage.rewriteData();
    }

    /**
     * Checks if Duke will end.
     * @return true
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
