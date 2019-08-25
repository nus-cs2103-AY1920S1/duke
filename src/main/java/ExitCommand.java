class ExitCommand implements Command {
    public ExitCommand() {}

    @Override
    public void execute(Ui uiManager, TaskList taskList, Storage storeManager) throws DukeException {
        uiManager.exit();
    }
}