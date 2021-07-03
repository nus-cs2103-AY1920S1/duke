/**
 * Represents a command which marks a task complete from the Task List.
 */
class DoneCommand implements Command {
    private Integer taskNumber;

    /**
     * Constructor for the Done Command, which instantiates an Integer of the task to be completed.
     * @param taskNumber The specific task's index.
     */
    public DoneCommand(Integer taskNumber) {
        this.taskNumber = taskNumber;
    }
    
    /**
     * Executes the Done Command, which completes a task from the taskList.
     * The completion by the taskList, has to be 0 base, hence the decrement in this.taskNumber.
     * 
     * <p>If the task is already completed, then we use uiManager to print out the given situation
     * 
     * @param uiManager Ui System which scans and prints for the User.
     * @param taskList TaskList, an ArrayList which stores Tasks.
     * @param storeManager Storage to serialize the TaskList into a Tasks.sav file.
     * @see TaskList#getTask(int, Ui)
     * @see Command#execute(Ui, TaskList, Storage)
     */
    @Override
    public void execute(Ui uiManager, TaskList taskList, Storage storeManager) throws RoriException {
        assert this.taskNumber != null : "taskNumber not found";
        Task task = taskList.getTask(this.taskNumber - 1);
        if (task.hasCompleted()) {
            uiManager.printAlreadyCompleted();
        } else {
            task.setCompleted(true);
            storeManager.store(taskList);
            uiManager.printDone(task);
        }
    }
}