class DeleteCommand implements Command {
    private Integer taskNumber;

    public DeleteCommand(Integer taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(Ui uiManager, TaskList taskList, Storage storeManager) throws DukeException {
        Task task = taskList.deleteTask(this.taskNumber - 1, uiManager);
        storeManager.store(taskList, uiManager);
        uiManager.printDelete(task, taskList.listSize());
    }
}