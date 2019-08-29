/**
 * Represents a command which provides help for the user - A word user guide.
 */
class HelpCommand implements Command {
    /**
     * Default Constructor
     */
    public HelpCommand() {}

    /**
     * This simple method executes the Help Command, which provides a user help.
     * It calls uiManager to print help.
     * 
     * @param uiManager Ui System which scans, prints and throws DukeExceptions for the User.
     * @param taskList TaskList, an ArrayList which stores Tasks. - Not in use.
     * @param storeManager Storage to serialize the TaskList into a Tasks.sav file. - Not in use
     * @see {@link DukeManager#initializeDuke()}
     */
    @Override
    public void execute(Ui uiManager, TaskList taskList, Storage storeManager) throws DukeException {
        uiManager.printHelp();
    }
}