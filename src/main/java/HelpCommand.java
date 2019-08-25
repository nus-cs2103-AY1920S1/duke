class HelpCommand implements Command {
    public HelpCommand() {}

    @Override
    public void execute(Ui uiManager, TaskList taskList, Storage storeManager) throws DukeException {
        uiManager.printHelp();
    }
}