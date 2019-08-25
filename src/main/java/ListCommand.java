class ListCommand implements Command {
    public ListCommand() {}

    @Override
    public void execute(Ui uiManager, TaskList taskList, Storage storeManager) throws DukeException {
        if(taskList.listSize() == 0) {
            uiManager.printEmptyList();
        } else {
            uiManager.printListStarter();
            for (int i = 0; i < taskList.listSize(); i++) {
                uiManager.printTask(i, taskList.getTask(i, uiManager));
            }
        }
    }
}