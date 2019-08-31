/**
 * Represents a command which provides help for the user - A word user guide.
 */
class HelpCommand implements Command {
    /**
     * This simple method executes the Help Command, which provides a user help.
     * It calls uiManager to print help.
     * 
     * @param uiManager Ui System which scans and prints for the User.
     * @param taskList TaskList, an ArrayList which stores Tasks. - Not in use.
     * @param storeManager Storage to serialize the TaskList into a Tasks.sav file. - Not in use
     * @see DukeManager#initializeDuke()
     * @see Command#execute(Ui, TaskList, Storage)
     */
    @Override
    public void execute(Ui uiManager, TaskList taskList, Storage storeManager) throws DukeException {
        uiManager.printHelp();
    }
}