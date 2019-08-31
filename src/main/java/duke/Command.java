/**
 * Represents a command created by the Parser and executed by DukeManager.
 */
interface Command {
    /**
     * This method executes a certain command or action that is read by the Parser and is called
     * in the DukeManager class.
     * 
     * <p>There is 6 different classes which implements this class.
     * AddCommand, DoneCommand, DeleteCommand, ListCommand, FindCommand, HelpCommand, ExitCommand
     * Each of them have different execution to this method representative to their names.
     * 
     * @param uiManager Ui System which scans and print for the User.
     * @param taskList TaskList, an ArrayList which stores Tasks.
     * @param storeManager Storage to serialize the TaskList into a Tasks.sav file.
     * @throws DukeException When a DukeException occurs
     */
    public void execute(Ui uiManager, TaskList taskList, Storage storeManager) throws DukeException;
}