/**
 * Represents an command which adds a task to the Task List.
 */
class AddCommand implements Command {
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the Add Command, which execute the addTask method from the taskList,
     * adding the task that was given when instantiating the class.
     * 
     * @param uiManager Ui System which scans and prints for the User.
     * @param taskList TaskList, an ArrayList which stores Tasks.
     * @param storeManager Storage to serialize the TaskList into a Tasks.sav file.
     * @throws DukeException When there is an error serializing.
     * @see Command#execute(Ui, TaskList, Storage)
     */
    @Override
    public void execute(Ui uiManager, TaskList taskList, Storage storeManager) throws DukeException {
        taskList.addTask(this.task);
        uiManager.printAddTask(task, taskList.listSize());
        storeManager.store(taskList);
        
    }

}