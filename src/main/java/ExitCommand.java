/**
 * Represents a command which exits the program
 */
class ExitCommand implements Command {
    /**
     * Default Constructor
     */
    public ExitCommand() {}

    /**
     * This method executes the Exit Command, which exits the program.
     * It calls uiManager to print out the exit, and the instance of this class will
     * stop the recursion in DukeManager
     * 
     * @param uiManager Ui System which scans, prints and throws DukeExceptions for the User.
     * @param taskList TaskList, an ArrayList which stores Tasks. - Not in use.
     * @param storeManager Storage to serialize the TaskList into a Tasks.sav file. - Not in use
     * @see {@link DukeManager#initializeDuke()}
     */
    @Override
    public void execute(Ui uiManager, TaskList taskList, Storage storeManager) {
        uiManager.exit();
    }
}