/**
 * Represents a command created by the Parser and executed by RoriManager.
 */
interface Command {
    /**
     * Executes a certain command or action that is read by the Parser and is called
     * in the RoriManager class.
     * 
     * <p>There is 6 different classes which implements this class.
     * AddCommand, DoneCommand, DeleteCommand, ListCommand, FindCommand, HelpCommand, ExitCommand
     * Each of them have different execution to this method representative to their names.
     * 
     * @param uiManager Ui System which scans and print for the User.
     * @param taskList TaskList, an ArrayList which stores Tasks.
     * @param storeManager Storage to serialize the TaskList into a Tasks.sav file.
     * @throws RoriException When a RoriException occurs
     */
    public void execute(Ui uiManager, TaskList taskList, Storage storeManager) throws RoriException;
}