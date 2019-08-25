class DeleteCommand implements Command {
    private String taskNumber;

    public DeleteCommand(String taskNumber) {
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
        Task task = taskList.deleteTask(taskNo - 1, uiManager);
        storeManager.store(taskList, uiManager);
        uiManager.printDelete(task, taskList.listSize());
    }
}