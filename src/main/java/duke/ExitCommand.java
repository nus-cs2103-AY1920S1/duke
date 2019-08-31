/**
 * Represents a command which exits the program.
 */
class ExitCommand implements Command {
    /**
     * Executes the Exit Command, which exits the program.
     * 
     * <p>It calls uiManager to print out the exit, and the instance of this class will
     * stop the recursion in DukeManager.
     * 
     * @param uiManager Ui System which scans and prints for the User.
     * @param taskList TaskList, an ArrayList which stores Tasks. - Not in use.
     * @param storeManager Storage to serialize the TaskList into a Tasks.sav file. - Not in use.
     * @see DukeManager#initializeDuke()
     * @see Command#execute(Ui, TaskList, Storage)
     */
    @Override
    public void execute(Ui uiManager, TaskList taskList, Storage storeManager) {
        uiManager.exit();
    }
}