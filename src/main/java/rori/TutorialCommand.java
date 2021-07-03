/**
 * Represents a command which provides a tutorial for the user - A word user guide.
 */
class TutorialCommand implements Command {
    private Command addTodo;
    private Command addDeadline;
    private Command addEvent;

    public TutorialCommand(Command addTodo, Command addDeadLine, Command addEvent) {
        this.addTodo = addTodo;
        this.addDeadline = addDeadLine;
        this.addEvent = addEvent;
    }

    /**
     * Executes the Tutorial Command, which provides an example by adding items to the save file.
     * 
     * @param uiManager Ui System which scans and prints for the User.
     * @param taskList TaskList, an ArrayList which stores Tasks. - Not in use.
     * @param storeManager Storage to serialize the TaskList into a Tasks.sav file. - Not in use
     * @see RoriManager#initializeRori()
     * @see Command#execute(Ui, TaskList, Storage)
     */
    @Override
    public void execute(Ui uiManager, TaskList taskList, Storage storeManager) throws RoriException {
        this.addTodo.execute(uiManager, taskList, storeManager);
        this.addDeadline.execute(uiManager, taskList, storeManager);
        this.addEvent.execute(uiManager, taskList, storeManager);
        uiManager.clear();
        uiManager.printTutorial();
    }
}