interface Command {
    public void execute(Ui uiManager, TaskList taskList, Storage storeManager) throws DukeException;
}