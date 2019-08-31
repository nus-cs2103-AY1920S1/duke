/**
 * Represents a command which deletes a task from the Task List.
 */
class DeleteCommand implements Command {
    private Integer taskNumber;

    /**
     * Constructor for Delete Command, which instantiates an Integer of the task to be deleted.
     * 
     * @param taskNumber Indicates the task number as in the Task List
     */
    public DeleteCommand(Integer taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Executes the Delete Command, which deletes a task from the taskList
     * The deletion by the taskList, has to be 0 base, hence our decrement in this.taskNumber
     * 
     * @param uiManager Ui System which scans and prints for the User.
     * @param taskList TaskList, an ArrayList which stores Tasks.
     * @param storeManager Storage to serialize the TaskList into a Tasks.sav file.
     * @see TaskList#deleteTask(int, Ui)
     * @see Command#execute(Ui, TaskList, Storage)
     */
    @Override
    public void execute(Ui uiManager, TaskList taskList, Storage storeManager) throws DukeException {
        Task task = taskList.deleteTask(this.taskNumber - 1);
        storeManager.store(taskList);
        uiManager.printDelete(task, taskList.listSize());
    }
}