/**
 * Represents a command which lists out the tasks left for the user.
 */
class ListCommand implements Command {
    /**
     * This method executes the List Command, which uses uiManager to list out
     * each line of the task from the taskList.
     * 
     * @param uiManager Ui System which scans and prints for the User.
     * @param taskList TaskList, an ArrayList which stores Tasks.
     * @param storeManager Storage to serialize the TaskList into a Tasks.sav file. - Not in use
     * @see Command#execute(Ui, TaskList, Storage)
     */
    @Override
    public void execute(Ui uiManager, TaskList taskList, Storage storeManager) throws DukeException {
        if (taskList.listSize() == 0) {
            uiManager.printEmptyList();
        } else {
            uiManager.printListStarter();
            for (int i = 0; i < taskList.listSize(); i++) {
                uiManager.printTask(i + 1, taskList.getTask(i));
            }
        }
    }
}