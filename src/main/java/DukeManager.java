import java.io.IOException;

class DukeManager {
    private Ui uiManager;
    private Storage storeManager;
    private Parser parseManager;
    private TaskList taskList;

    private boolean isActive;
    
    public DukeManager() throws DukeException, IOException, ClassNotFoundException {
        this.uiManager = new Ui();
        this.storeManager = new Storage("Tasks.sav");
        this.parseManager = new Parser();
        this.taskList = this.storeManager.retrieve(this.uiManager);
        this.isActive = false;
    }

    public void initializeDuke() throws DukeException {
        uiManager.printWelcome();
        this.isActive = true;
        while(isActive) {
            uiManager.printWhatToDo();
            String input = uiManager.readLine();
            Command command = parseManager.parseToCommand(input, uiManager);
            command.execute(this.uiManager, this.taskList, this.storeManager);
            if(command instanceof ExitCommand) {
                isActive = false;
            }
            uiManager.printEmpty();
        }
    }
}