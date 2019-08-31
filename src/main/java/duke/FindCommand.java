/**
 * Represents a command for finding a particular String regardless of Capitalization.
 */
class FindCommand implements Command {
    private String findString;

    /**
     * Constructor for FindCommand.
     * @param findString The String the user wants to find.
     */
    public FindCommand(String findString) {
        this.findString = findString;
    }

    /**
     * Executes the Find command to iterate through the task list for the tasks which 
     * contain the keyword the user input.
     * 
     * @param uiManager Ui System which scans and prints for the User.
     * @param taskList TaskList, an ArrayList which stores Tasks.
     * @param storeManager Storage to serialize the TaskList into a Tasks.sav file. - Not in use
     * @throws DukeException When failing to get the task.
     * @see Command#execute(Ui, TaskList, Storage)
     */
    @Override
    public void execute(Ui uiManager, TaskList taskList, Storage storeManager) throws DukeException {
        int index = 1;
        uiManager.printFinding(this.findString);
        for (int i = 0; i < taskList.listSize(); i++) {
            Task currTask = taskList.getTask(i);
            if (checkContainsInsensitive(currTask, this.findString)) {
                uiManager.printTask(index, currTask);
                index++;    
            }
        }

        // Print nothing found
        if (index == 1) {
            uiManager.printNotFound();
        }
    }

    /**
     * Returns a boolean whether the current iteration of the task contain the given String.
     * 
     * @param task The current iteration of a Task in the task list.
     * @param findString The given String to be found by the user
     * @return a boolean whether the task contains the String.
     */
    private boolean checkContainsInsensitive(Task task, String findString) {
        return task.getTaskString().toLowerCase().contains(findString.toLowerCase());
    }
}