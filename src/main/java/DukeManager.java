import java.io.IOException;

/**
 * Represents a manager to manage all of the different classes, and act's as the
 * initialization stage of the program.
 */
class DukeManager {
    private Ui uiManager;
    private Storage storeManager;
    private Parser parseManager;
    private TaskList taskList;

    private boolean isActive;
    
    /**
     * Constructor for DukeManager, which instantiates several other classes as well.
     * 
     * @throws DukeException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public DukeManager() throws DukeException, IOException, ClassNotFoundException {
        this.uiManager = new Ui();
        this.storeManager = new Storage("Tasks.sav");
        this.parseManager = new Parser();
        this.taskList = this.storeManager.retrieve(this.uiManager);
        this.isActive = false;
    }

    /**
     * This method initializes the Duke program, and recursively reads input
     * and produces and output, unless encountered with a DukeException.
     * <p>
     * There are 8 different Commands the user can input:
     * list, done, delete, todo, deadline, event, help, bye.
     * This method will end when the user inputs 'bye' or anything else which causes a DukeException.
     * 
     * @throws DukeException
     */
    public void initializeDuke() throws DukeException {
        uiManager.printWelcome();
        this.isActive = true;
        while(isActive) {
            uiManager.printWhatToDo();
            String input = uiManager.readLine();
            Command command = parseManager.parseToCommand(input, uiManager);
            command.execute(this.uiManager, this.taskList, this.storeManager);
            if (command instanceof ExitCommand) {
                isActive = false;
            }
            uiManager.printEmpty();
        }
    }
}