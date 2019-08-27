class DoneCommand implements Command {
    private Integer taskNumber;

    public DoneCommand(Integer taskNumber) {
        this.taskNumber = taskNumber;
    }
    
    @Override
    public void execute(Ui uiManager, TaskList taskList, Storage storeManager) throws DukeException {
        Task task = taskList.getTask(this.taskNumber - 1, uiManager);
        if(task.isCompleted()) {
            uiManager.printAlreadyCompleted();
        } else {
            task.setCompleted(true);
            storeManager.store(taskList, uiManager);
            uiManager.printDone(task);
        }
    }
}