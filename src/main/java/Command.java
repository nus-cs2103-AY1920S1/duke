public abstract class Command {
    String stringCommand;

    /**
     * Constructor for ByeCommand
     * @param stringCommand
     */
    public Command(String stringCommand) {
        this.stringCommand = stringCommand;
    }

    /**
     * Executes the command by using the three arguments provided
     * @param taskList
     * @param ui
     * @param storage
     */
    public abstract void execute(TaskList taskList, UI ui, Storage storage);

    /**
     * Checks if Duke will end.
     * @return
     */
    public abstract boolean isExit();
}
