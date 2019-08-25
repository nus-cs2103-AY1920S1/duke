class DoneCommand implements Command {
    private String taskNumber;

    public DoneCommand(String taskNumber) {
        this.taskNumber = taskNumber;
    }
    
    @Override
    public void execute(Ui uiManager, TaskList taskList, Storage storeManager) throws DukeException {
        Integer taskNo = null;
        try {
            taskNo = Integer.parseInt(this.taskNumber);
        } catch (Exception e) {
            uiManager.throwMissingNumberError("Done");
        }
        if(taskNo == null) {
            uiManager.throwMissingNumberError("Done");
        }
        Task task = taskList.getTask(taskNo - 1, uiManager);
        if(task.isCompleted()) {
            uiManager.printAlreadyCompleted();
        } else {
            task.setCompleted(true);
            storeManager.store(taskList, uiManager);
            uiManager.printDone(task);
        }
    }
}