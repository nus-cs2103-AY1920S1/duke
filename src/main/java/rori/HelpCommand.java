
/**
 * Represents a command which provides help for the user - A word user guide.
 */
class HelpCommand implements Command {
    
    private Action action;

    public HelpCommand() {
        this.action = null;
    }

    public HelpCommand(Action action) {
        this.action = action;
    }

    /**
     * Executes the Help Command, which provides a user help, calling uiManager to print help.
     * 
     * @param uiManager Ui System which scans and prints for the User.
     * @param taskList TaskList, an ArrayList which stores Tasks. - Not in use.
     * @param storeManager Storage to serialize the TaskList into a Tasks.sav file. - Not in use
     * @see RoriManager#initializeRori()
     * @see Command#execute(Ui, TaskList, Storage)
     */
    @Override
    public void execute(Ui uiManager, TaskList taskList, Storage storeManager) throws RoriException {
        if (this.action == null) {
            uiManager.printHelp();
        } else {
            uiManager.printActionHelp(action);
        }
    }
}